package dk.aau.cs.experimentProfile;

import dk.aau.cs.SSB.provGenerator.Granularity;

public class ExperimentTest extends ExperimentProfile {

	public ExperimentTest() {
		SchemaGranularity lineorder = new SchemaGranularity(Granularity.LOWEST);
		schema.put("lineorder", lineorder);
		SchemaGranularity customer = new SchemaGranularity(Granularity.LOWEST);
		schema.put("customer", customer);
		SchemaGranularity part = new SchemaGranularity(Granularity.LOWEST);
		schema.put("part", part);
		SchemaGranularity supplier = new SchemaGranularity(Granularity.LOWEST);
		schema.put("supplier", supplier);
		SchemaGranularity date = new SchemaGranularity(Granularity.SPLIT_ON_ATTRIBUTE,4);
		schema.put("date", date);
	}
	
}
