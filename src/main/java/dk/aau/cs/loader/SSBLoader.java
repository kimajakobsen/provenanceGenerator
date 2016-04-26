package dk.aau.cs.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
import dk.aau.cs.SSB.provGenerator.ProvGenerator;
import dk.aau.cs.SSB.provGenerator.ProvenanceBuilder;
import dk.aau.cs.SSB.schema.Schema;
import dk.aau.cs.SSB.schema.SchemaBuilder;
import dk.aau.cs.main.Config;

public class SSBLoader extends AbstractLoader {

	public SSBLoader(String file) {
		super(file);
	}
	
	public SSBLoader(List<String> files) {
		super(files);
	}
	
	@Override
	public void loadToTDB(String location) {
		System.out.println("writing "+ getModelContainerSize() +" triples to "+ location);
		Dataset dataset = TDBFactory.createDataset(location) ;
		for (Entry<String, Model> entry : getModelContainer().entrySet()) {
			dataset.begin(ReadWrite.WRITE) ;
			//countTriplesInTDB(dataset);
			
			Model model = ModelFactory.createDefaultModel();
			model.add(dataset.getNamedModel(entry.getKey()));
			model.add(entry.getValue());
			//System.out.println("writing "+ model.size() + " triples to graph " + entry.getKey() );
			dataset.addNamedModel(entry.getKey(), model);
			dataset.commit();
		}
		dataset.end();
	}
	
	public void countTriplesInTDB(Dataset dataset) {
		QueryExecution qExec = QueryExecutionFactory.create(
	             "select (count(*) as ?count) { GRAPH ?G {?a ?b ?c} }",   dataset);
	         ResultSet rs = qExec.execSelect() ;
	         //ResultSetFormatter.out(rs) ;
	         System.out.println(ResultSetFormatter.asText(rs));
	}
	
	public void run (boolean provenance) {
		BufferedReader bufferReader = null;
		String rawLine = "";
		String cvsSplitBy = "\\|";
		
		//Get cube metadata triples
		Model cubeStructure = QB4OLAPGenerator.getStructureTriples();
		insertIntoModelContainer(Config.getCubeStructureGraphName(), cubeStructure);

		for (String csvFile : files) {
			try {
				bufferReader = new BufferedReader(new FileReader(csvFile));
				Schema schema = new SchemaBuilder().build(csvFile);
				
				//Get cube metadata triples depending on dimensions
				QB4OLAPGenerator qb4olapGenerator = new QB4OLAPGenerator(schema);
				//cubeStructure = qb4olapGenerator.getStructureTriples();
				//insertIntoModelContainer(schema.getCubeStructureGraphName(), cubeStructure);
				
				
				while ((rawLine = bufferReader.readLine()) != null) {
					String[] line = rawLine.split(cvsSplitBy);
					Resource subject = schema.getIRI(line);
					
					insertIntoModelContainer(Config.getCubeInstanceGraphName(), qb4olapGenerator.getInstanceTriples(subject));
					
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
						} else { // Literal
							object = schema.createLiteralWithType(schemaPropertyIndex,field); 
							s = ResourceFactory.createStatement(subject, predicate, object);
							informationResource.add(s);
							
							if (provenance) {
								ProvGenerator provenanceGenerator = ProvenanceBuilder.build(schema);
								Model provenanceModel = provenanceGenerator.getProvenanceTriples(s);
								insertIntoModelContainer(schema.getProvenanceGraphName(), provenanceModel);
								insertIntoModelContainer(provenanceGenerator.getProvenanceIdentifier(), informationResource);
							} else {
								insertIntoModelContainer("", informationResource);
							}
						}
						schemaPropertyIndex++;
					}
					if (getModelContainerSize() > Config.getBatchSize()) {
						loadToTDB(Config.getDatabasePath());
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
		loadToTDB(Config.getDatabasePath());
	}

}
