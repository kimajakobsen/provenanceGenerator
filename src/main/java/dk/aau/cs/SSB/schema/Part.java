package dk.aau.cs.SSB.schema;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;

import dk.aau.cs.helper.QB4OLAP;
import dk.aau.cs.main.Config;


public class Part extends Schema {


	public Part() {
		super("part");
		schema.put(0, new SchemaPropertySet("partkey", 	"", true,	"int"));
		schema.put(1, new SchemaPropertySet("name", 	"", false,	"str"));
		schema.put(2, new SchemaPropertySet("mfgr", 	"", false,	"str"));
		schema.put(3, new SchemaPropertySet("category", "", false,	"str"));
		schema.put(4, new SchemaPropertySet("brand", 	"", false,	"str"));
		schema.put(5, new SchemaPropertySet("color", 	"", false,	"str"));
		schema.put(6, new SchemaPropertySet("type", 	"", false,	"str"));
		schema.put(7, new SchemaPropertySet("size", 	"", false,	"int"));
		schema.put(8, new SchemaPropertySet("container","", false,	"str"));
	}

	@Override
	public Model getCubeInstanceMetadataTriples(Resource subject) {
		Model model = ModelFactory.createDefaultModel();
		RDFNode part = ResourceFactory.createResource(Config.getNamespace()+"part");
		model.add(subject, QB4OLAP.memberOf, part );
		return model;
	}
}
