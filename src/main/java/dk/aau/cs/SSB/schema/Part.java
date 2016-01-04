package dk.aau.cs.SSB.schema;


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

}
