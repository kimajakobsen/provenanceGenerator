package dk.aau.cs.SSB.schema;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;

import dk.aau.cs.helper.QB4OLAP;
import dk.aau.cs.main.Config;

public class Date extends Schema {

	public Date() {
		super("date");
		schema.put(0, new SchemaPropertySet("datekey", 				"", true,	"int"));
		schema.put(1, new SchemaPropertySet("date", 				"", false,	"date"));
		schema.put(2, new SchemaPropertySet("dayofweek", 			"", false,	"str"));
		schema.put(3, new SchemaPropertySet("month", 				"", false,	"str"));
		schema.put(4, new SchemaPropertySet("year", 				"", false,	"int"));
		schema.put(5, new SchemaPropertySet("yeamonthnum", 			"", false,	"int"));
		schema.put(6, new SchemaPropertySet("yearmonth", 			"", false,	"str"));
		schema.put(7, new SchemaPropertySet("daynumweek", 			"", false,	"int"));
		schema.put(8, new SchemaPropertySet("daynummonth",			"", false,	"int"));
		schema.put(9, new SchemaPropertySet("daynumyear",			"", false,	"int"));
		schema.put(10, new SchemaPropertySet("monthnuminyear",		"", false,	"int"));
		schema.put(11, new SchemaPropertySet("weeknuminyear",		"", false,	"int"));
		schema.put(12, new SchemaPropertySet("sellingseason",		"", false,	"str"));
		schema.put(13, new SchemaPropertySet("lastdayinweek",		"", false,	"bool"));
		schema.put(14, new SchemaPropertySet("notlastdayinmonth",	"", false,	"bool"));
		schema.put(15, new SchemaPropertySet("holiday",				"", false,	"bool"));
		schema.put(16, new SchemaPropertySet("weekday",				"", false,	"bool"));
	}
	
	@Override
	public Model getCubeInstanceMetadataTriples(Resource subject) {
		Model model = ModelFactory.createDefaultModel();
	
		RDFNode orderDate = ResourceFactory.createResource(Config.getNamespace()+"orderDate");
		model.add(subject, QB4OLAP.memberOf, orderDate );
		
		RDFNode commitDate = ResourceFactory.createResource(Config.getNamespace()+"commitDate");
		model.add(subject, QB4OLAP.memberOf, commitDate );
		return model;
	}
}
