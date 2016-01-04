package dk.aau.cs.SSB.schema;


public class Lineorder extends Schema {

	public Lineorder() {
		super("lineorder");
		schema.put(0, new SchemaPropertySet("orderkey", 		"", 		true,	"int"));
		schema.put(1, new SchemaPropertySet("linenumber", 		"", 		true,	"int"));
		schema.put(2, new SchemaPropertySet("custkey", 			"customer", false,	"int"));
		schema.put(3, new SchemaPropertySet("partkey", 			"part", 	false,	"int"));
		schema.put(4, new SchemaPropertySet("suppkey", 			"supplier", false,	"int"));
		schema.put(5, new SchemaPropertySet("orderdate", 		"date", 	false,	"int"));
		schema.put(6, new SchemaPropertySet("orderpriority", 	"", 		false,	"str"));
		schema.put(7, new SchemaPropertySet("shippriority", 	"", 		false,	"int"));
		schema.put(8, new SchemaPropertySet("quantity",			"", 		false,	"int"));
		schema.put(9, new SchemaPropertySet("extendedprice",	"", 		false,	"int"));
		schema.put(10, new SchemaPropertySet("ordtotalprice",	"", 		false,	"int"));
		schema.put(11, new SchemaPropertySet("discount",		"", 		false,	"int"));
		schema.put(12, new SchemaPropertySet("revenue",			"", 		false,	"int"));
		schema.put(13, new SchemaPropertySet("supplycost",		"", 		false,	"int"));
		schema.put(14, new SchemaPropertySet("tax",				"", 		false,	"int"));
		schema.put(15, new SchemaPropertySet("commitdate",		"date", 	false,	"int"));
		schema.put(16, new SchemaPropertySet("shipmode",		"", 		false,	"str"));
	}

}
