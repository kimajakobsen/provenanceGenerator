package dk.aau.cs.SSB.schema;

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
		schema.put(14, new SchemaPropertySet("lastdayinmonth",		"", false,	"bool"));
		schema.put(15, new SchemaPropertySet("holiday",				"", false,	"bool"));
		schema.put(16, new SchemaPropertySet("weekday",				"", false,	"bool"));
	}
	
// Fixed in the generator.
//	public RDFNode getObject(int index, String value) throws InvalidAttributeValueException {
//		//inverts the lastdayinmonth value because it is generated reverse
//		if (index == 14) {
//			if (value.equals("0")) {
//				value = "1";
//			} else {
//				value = "0";
//			}
//		} 
//		return super.getObject(index, value);
//		
//	}

}
