package dk.aau.cs.experimentProfile;

import java.util.HashMap;

import dk.aau.cs.SSB.provGenerator.ProvenanceTripleGraphSize;

public abstract class ExperimentProfile {
	HashMap<String, SchemaGranularity> schema = new HashMap<String, SchemaGranularity>();

	public SchemaGranularity getSchemaGranularity(String schemaName) {
		return schema.get(schemaName);
	}
	
	public abstract ProvenanceTripleGraphSize getProvenanceGraphSize();
}
