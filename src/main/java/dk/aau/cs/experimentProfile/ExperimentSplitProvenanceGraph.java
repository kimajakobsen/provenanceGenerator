package dk.aau.cs.experimentProfile;

import dk.aau.cs.SSB.provGenerator.Granularity;
import dk.aau.cs.SSB.provGenerator.MinimalProvenanceGraph;
import dk.aau.cs.SSB.provGenerator.ProvenanceTripleGraphSize;

public class ExperimentSplitProvenanceGraph extends ExperimentProfile {

	public ExperimentSplitProvenanceGraph() {
		SchemaGranularity lineorder = new SchemaGranularity(Granularity.SPLIT_ON_ATTRIBUTE,2);
		schema.put("lineorder", lineorder);
		SchemaGranularity customer = new SchemaGranularity(Granularity.SPLIT_ON_ATTRIBUTE,5);
		schema.put("customer", customer);
		SchemaGranularity part = new SchemaGranularity(Granularity.SPLIT_ON_ATTRIBUTE,4);
		schema.put("part", part);
		SchemaGranularity supplier = new SchemaGranularity(Granularity.SPLIT_ON_ATTRIBUTE,5);
		schema.put("supplier", supplier);
		SchemaGranularity date = new SchemaGranularity(Granularity.SPLIT_ON_ATTRIBUTE,4);
		schema.put("date", date);
	}

	@Override
	public ProvenanceTripleGraphSize getProvenanceGraphSize() {
		return new MinimalProvenanceGraph();
	}
}
