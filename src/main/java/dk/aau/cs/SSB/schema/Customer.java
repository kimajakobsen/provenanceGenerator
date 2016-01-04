package dk.aau.cs.SSB.schema;


public class Customer extends Schema {

	public Customer() {
		super("customer");
		schema.put(0, new SchemaPropertySet("custkey", 	"", true, "int"));
		schema.put(1, new SchemaPropertySet("name", 	"", false, "str"));
		schema.put(2, new SchemaPropertySet("address", 	"", false, "str"));
		schema.put(3, new SchemaPropertySet("city", 	"", false, "str"));
		schema.put(4, new SchemaPropertySet("nation", 	"", false, "str"));
		schema.put(5, new SchemaPropertySet("region", 	"", false, "str"));
		schema.put(6, new SchemaPropertySet("phone", 	"", false, "other"));
		schema.put(7, new SchemaPropertySet("mktsegment", "", false, "str"));
	}
}
