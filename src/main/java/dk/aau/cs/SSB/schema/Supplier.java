package dk.aau.cs.SSB.schema;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;

import dk.aau.cs.helper.QB4OLAP;
import dk.aau.cs.main.Config;

public class Supplier extends Schema {

	public Supplier() {
		super("supplier");
		schema.put(0, new SchemaPropertySet("supkey", 	"", true,	"int"));
		schema.put(1, new SchemaPropertySet("name", 	"", false,	"str"));
		schema.put(2, new SchemaPropertySet("address", 	"", false,	"str"));
		schema.put(3, new SchemaPropertySet("city", 	"", false,	"str"));
		schema.put(4, new SchemaPropertySet("nation", 	"", false,	"str"));
		schema.put(5, new SchemaPropertySet("region", 	"", false,	"str"));
		schema.put(6, new SchemaPropertySet("phone", 	"", false,	"other"));
	}
	
	@Override
	public Model getCubeInstanceMetadataTriples(Resource subject) {
		Model model = ModelFactory.createDefaultModel();
		RDFNode supplier = ResourceFactory.createResource(Config.getNamespace()+"supplier");
		model.add(subject, QB4OLAP.memberOf, supplier );
		return model;
	}
}
