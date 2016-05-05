package dk.aau.cs.experimentProfile;

import dk.aau.cs.SSB.provGenerator.Granularity;

public class SchemaGranularity {
	private Granularity granularity = null;
	private int index;

	public SchemaGranularity(Granularity granularity) {
		this.granularity=granularity;
	}
	
	public SchemaGranularity(Granularity granularity, int index) {
		this.granularity=granularity;
		this.index=index;
	}
	
	public Granularity getType() {
		return granularity;
	}
	
	public int getAttributeIndex() {
		return index;
	}
}
