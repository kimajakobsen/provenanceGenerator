package dk.aau.cs.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.tdb.TDBFactory;

import dk.aau.cs.SSB.cubeGenerator.QB4OLAPGenerator;
import dk.aau.cs.SSB.provGenerator.ProvenanceGenerator;
import dk.aau.cs.SSB.provGenerator.ProvenanceGeneratorBuilder;
import dk.aau.cs.SSB.provGenerator.ProvenanceTripleGraphSize;
import dk.aau.cs.SSB.schema.Schema;
import dk.aau.cs.SSB.schema.SchemaBuilder;
import dk.aau.cs.experimentProfile.ExperimentProfile;
import dk.aau.cs.helper.DatasetMetadata;
import dk.aau.cs.main.Config;

public class SSBLoader extends AbstractLoader {

	public SSBLoader(List<String> files) {
		super(files);
	}
	
	@Override
	public void writeToTDB(String location, HashMap<String,Model> modelContainer) {
		System.out.println("writing "+ getModelContainerSize() +" triples to "+ location);
		Dataset dataset = TDBFactory.createDataset(location) ;
		dataset.begin(ReadWrite.WRITE) ;
		
		for (Entry<String, Model> entry : modelContainer.entrySet()) {
			Model model = ModelFactory.createDefaultModel();
			model.add(dataset.getNamedModel(entry.getKey()));
			//When a large model is loaded into memory (done to ensure that data is not overwritten)
			if (model.size() > 0) {
				System.out.println("loading "+ model.size() + " triples from graph" + entry.getKey());
				model.add(entry.getValue());
				System.out.println("writing "+ model.size() + " triples to graph " + entry.getKey() );
			} else {
				model.add(entry.getValue());
			}
			
			dataset.addNamedModel(entry.getKey(), model);
			model.close();
		}
		//countTriplesInTDB(dataset);
		dataset.commit();
		dataset.end();
	}
	
	@SuppressWarnings("unused")
	private void countTriplesInTDB(Dataset dataset) {
		 String qs1 = "SELECT (count(*) as ?count) where { graph ?g {?s ?p ?o}}" ;
		 QueryExecution qExec = QueryExecutionFactory.create(qs1, dataset) ;
	     ResultSet rs = qExec.execSelect() ;
	     ResultSetFormatter.out(rs) ;
	}

	public void run (ExperimentProfile profile) {
		BufferedReader bufferReader = null;
		String rawLine = "";
		String cvsSplitBy = "\\|";
		
		DatasetMetadata datasetMetadata = new DatasetMetadata();
		datasetMetadata.setPath(Config.getDatabasePath());
		
		//Get cube metadata triples
		Instant start = Instant.now();
		Model cubeStructure = QB4OLAPGenerator.getStructureTriples();
		insertIntoModelContainer(Config.getCubeStructureGraphName(), cubeStructure);
		datasetMetadata.setNumberOfStructureMetadataTriples(cubeStructure);

		for (String csvFile : files) {
			System.out.println("reading file "+csvFile);
			try {
				bufferReader = new BufferedReader(new FileReader(csvFile));
				Schema schema = new SchemaBuilder().build(csvFile);
				datasetMetadata.setGranularity(schema,profile);
				
				//Get cube metadata triples depending on dimensions
				QB4OLAPGenerator qb4olapGenerator = new QB4OLAPGenerator(schema);
				ProvenanceTripleGraphSize graphSize = profile.getProvenanceGraphSize();
				ProvenanceGenerator provenanceGenerator = ProvenanceGeneratorBuilder.build(graphSize ,schema, profile, datasetMetadata);
				
				while ((rawLine = bufferReader.readLine()) != null) {
					String[] line = rawLine.split(cvsSplitBy);                                                                                                            
					Resource subject = schema.getIRI(line);
					datasetMetadata.incrementNumberOfFacts(schema);
					//System.out.println("Subject: "+subject.toString()+" Attribute: " + line[4]);
					
					Model provenanceModel = provenanceGenerator.getProvenanceTriples(line);
					insertIntoModelContainer(schema.getProvenanceGraphName(), provenanceModel);
					datasetMetadata.setNumberOfProvenanceTriples(schema,provenanceModel);
					
					Model instanceMetadataModel = qb4olapGenerator.getInstanceTriples(subject);
					insertIntoModelContainer(Config.getCubeInstanceGraphName(), instanceMetadataModel);
					
					int schemaPropertyIndex = 0;
					for (String field : line) {
						Model informationResource = ModelFactory.createDefaultModel();
						Model cubeInstanceMetadata = ModelFactory.createDefaultModel();
						Property predicate = schema.getProperty(schemaPropertyIndex);
						Statement s;
						RDFNode object;
						
						if (!schema.getObjectPropertyName(schemaPropertyIndex).equals("")) { //object
							 object = ResourceFactory.createResource(Config.getNamespace()+schema.getObjectPropertyName(schemaPropertyIndex)+field+"/"); // objectProperty
							 s = ResourceFactory.createStatement(subject, predicate, object);
							 cubeInstanceMetadata.add(s);
							 insertIntoModelContainer(Config.getCubeInstanceGraphName(), cubeInstanceMetadata);
							 datasetMetadata.addNumberOfInstanceMetadataTriples(cubeInstanceMetadata.size());
						} else { // Literal
							object = schema.createLiteralWithType(schemaPropertyIndex,field); 
							s = ResourceFactory.createStatement(subject, predicate, object);
							informationResource.add(s);
							String provenanceIdentifier = provenanceGenerator.getProvenanceIdentifier(s);
							insertIntoModelContainer(provenanceIdentifier, informationResource);
							datasetMetadata.addNumberOfInformationTriples(schema,informationResource.size());
							datasetMetadata.addProveanceIdentifier(schema,provenanceIdentifier);
						}
						schemaPropertyIndex++;
					}
					//System.out.println(getModelContainerSize());
					if (getModelContainerSize() > Config.getBatchSize()) {
						writeToTDB(Config.getDatabasePath(),getModelContainer());
						resetModelContainer();
					}
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bufferReader != null) {
					try {
						bufferReader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		writeToTDB(Config.getDatabasePath(),getModelContainer());
		writeToTDB(Config.getDatabasePath(),getLargeModelContainer());
		datasetMetadata.setGenerationDuration(Duration.between(start, Instant.now()));
		System.out.println("done");
		if (Config.isWriteToDatabase()) {
			datasetMetadata.writeToDatabase();
		} else {
			datasetMetadata.writeToFile();
		}
		
	}
}
