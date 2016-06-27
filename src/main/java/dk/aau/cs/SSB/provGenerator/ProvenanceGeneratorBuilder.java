package dk.aau.cs.SSB.provGenerator;

import org.apache.commons.lang3.NotImplementedException;

import dk.aau.cs.SSB.schema.Schema;
import dk.aau.cs.experimentProfile.ExperimentProfile;
import dk.aau.cs.experimentProfile.SchemaGranularity;

public class ProvenanceGeneratorBuilder {

	public static ProvenanceGenerator build(ProvenanceTripleGraphSize graphSize, Schema schema, Granularity type, int attributeIndex) {
		if (type != Granularity.SPLIT_ON_ATTRIBUTE) {
			throw new NotImplementedException("If clause not implemented");
		}
		return new Split(graphSize, schema, attributeIndex);
	}
	
	public static ProvenanceGenerator build(ProvenanceTripleGraphSize graphSize, Schema schema, ExperimentProfile profile) {
		
		SchemaGranularity granularity = profile.getSchemaGranularity(schema.getIdentifierName());
		Granularity type = granularity.getType();
		if (type == Granularity.SPLIT_ON_ATTRIBUTE) {
			int attributeIndex = granularity.getAttributeIndex();
			return new Split(graphSize ,schema, attributeIndex);
		}
		
		if (type == Granularity.HIGHEST) {
			return new Highest(graphSize, schema);
		}
		if (type == Granularity.LOWEST) {
			return new Lowest(graphSize, schema);
		}
		throw new IllegalArgumentException("unknown type "+type);
	}
}
