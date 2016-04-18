package dk.aau.cs.SSB.cubeGenerator;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;

import dk.aau.cs.SSB.schema.Schema;
import dk.aau.cs.main.Config;

public class QB4OLAPGenerator {
	private Schema schema;
	private String identifierName;

	public String getIdentifierName() {
		return identifierName;
	}

	public QB4OLAPGenerator(Schema schema) {
		this.schema = schema;
		this.identifierName = schema.getIdentifierName();
	}

	public static Model getStructureTriples() {
		Model model = ModelFactory.createDefaultModel();
		model.read(Config.getOntologyPath(), "TURTLE") ;
		return model;
	}

	public Model getInstanceTriples(Resource subject) {
		return schema.getCubeInstanceMetadataTriples(subject);
	}
}
