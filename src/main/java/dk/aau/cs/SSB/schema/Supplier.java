package dk.aau.cs.SSB.schema;


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

}
