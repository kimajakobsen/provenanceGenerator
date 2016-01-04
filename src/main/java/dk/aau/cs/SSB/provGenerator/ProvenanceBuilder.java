package dk.aau.cs.SSB.provGenerator;

import dk.aau.cs.SSB.schema.Schema;

public class ProvenanceBuilder {
	

	private ProvenanceBuilder() {}
	

	public static ProvGenerator build(Schema schema) {
		String name = schema.getIdentifierName();
		
		if (name == "lineorder") {
			return null;
		} else if (name == "customer") {
			return new Customer();
		} else if (name == "date") {
			return null;
		} else if (name == "supplier") {
			return null;
		} else if (name == "part") {
			return null;
		} else {
			return null;
		}
		
		
	}
	

}
