package dk.aau.cs.SSB.provGenerator;

import dk.aau.cs.SSB.schema.Schema;

public class ProvenanceGeneratorBuilder {

	public static ProvenanceGenerator build(Schema schema, Granularity type, int attributeIndex) {
		if (type != Granularity.SPLIT_ON_ATTRIBUTE) {
			throw new IllegalArgumentException("To many arguments, only SPLIt can have an attribute");
		}
		return new Split(schema, attributeIndex);
	}
	
	public static ProvenanceGenerator build(Schema schema, Granularity type) {
		
		if (type == Granularity.SPLIT_ON_ATTRIBUTE) {
			throw new IllegalArgumentException("missing attribute parameter, on which attribute should the provenance be split?");
		}
		
		if (type == Granularity.HIGHEST) {
			return new Highest(schema);
		}
		if (type == Granularity.LOWEST) {
			return new Lowest(schema);
		}
		throw new IllegalArgumentException("unknown type "+type);
	}
}
