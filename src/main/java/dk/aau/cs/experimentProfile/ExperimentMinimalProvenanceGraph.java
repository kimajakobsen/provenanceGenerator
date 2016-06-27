package dk.aau.cs.experimentProfile;

import dk.aau.cs.SSB.provGenerator.Granularity;
import dk.aau.cs.SSB.provGenerator.MinimalProvenanceGraph;
import dk.aau.cs.SSB.provGenerator.ProvenanceTripleGraphSize;

public class ExperimentMinimalProvenanceGraph extends ExperimentProfile {

	public ExperimentMinimalProvenanceGraph() {
		SchemaGranularity lineorder = new SchemaGranularity(Granularity.SPLIT_ON_ATTRIBUTE,2);
		schema.put("lineorder", lineorder);
		SchemaGranularity customer = new SchemaGranularity(Granularity.LOWEST);
		schema.put("customer", customer);
		SchemaGranularity part = new SchemaGranularity(Granularity.LOWEST);
		schema.put("part", part);
		SchemaGranularity supplier = new SchemaGranularity(Granularity.LOWEST);
		schema.put("supplier", supplier);
		SchemaGranularity date = new SchemaGranularity(Granularity.LOWEST);
		schema.put("date", date);
	}

	@Override
	public ProvenanceTripleGraphSize getProvenanceGraphSize() {
		return new MinimalProvenanceGraph();
	}
}
