package dk.aau.cs.loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.tdb.TDBFactory;

import dk.aau.cs.SSB.provGenerator.ProvGenerator;
import dk.aau.cs.SSB.provGenerator.ProvenanceBuilder;
import dk.aau.cs.SSB.schema.Schema;
import dk.aau.cs.SSB.schema.SchemaBuilder;

public class SSBLoader extends AbstractLoader {

	public SSBLoader(String file) {
		super(file);
	}
	
	public SSBLoader(List<String> files) {
		super(files);
	}
	
	@Override
	public void loadToTDB(String location) {
		Dataset dataset = TDBFactory.createDataset(location) ;
		System.out.println("there are "+getModelContainer().entrySet().size()+" Models");
		for (Entry<String, Model> entry : getModelContainer().entrySet()) {
			dataset.begin(ReadWrite.WRITE) ;
			System.out.println("Writing the graph "+entry.getKey()+" storage.");
			dataset.addNamedModel(entry.getKey(), entry.getValue());
			dataset.commit() ;
		}
		dataset.end();
		 
	}
	
	
	public void run (boolean provenance) {
		BufferedReader bufferReader = null;
		String rawLine = "";
		String cvsSplitBy = "\\|";

		for (String csvFile : files) {
			try {
				bufferReader = new BufferedReader(new FileReader(csvFile));
				Schema schema = new SchemaBuilder().build(csvFile);
				
				while ((rawLine = bufferReader.readLine()) != null) {
					String[] line = rawLine.split(cvsSplitBy);
					
					Resource subject = schema.getIRI(line);
					
					int schemaPropertyIndex = 0;
					for (String field : line) {
						Property predicate = schema.getProperty(schemaPropertyIndex);
						RDFNode object = schema.getObject(schemaPropertyIndex,field);
						
						Model informationResource = ModelFactory.createDefaultModel();
						Statement s = ResourceFactory.createStatement(subject, predicate, object);
						informationResource.add(s);
						
						if (provenance) {
							ProvGenerator provenanceGenerator = ProvenanceBuilder.build(schema);
							Model temp = provenanceGenerator.getProvenanceTriples(s);
							insertIntoModelContainer(schema.getProvenanceGraphName(), temp);
							insertIntoModelContainer(provenanceGenerator.getProvenanceIdentifier(), informationResource);
						} else {
							insertIntoModelContainer("", informationResource);
						}
						schemaPropertyIndex++;
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
	}

}
