package dk.aau.cs.SSB.provGenerator;

import dk.aau.cs.SSB.schema.Schema;

public class ProvenanceBuilder {
	

	private ProvenanceBuilder() {}
	

	public static ProvGenerator build(Schema schema) {
		String name = schema.getIdentifierName();
		
		if (name == "lineorder") {
			return new Lineorder();
		} else if (name == "customer") {
			return new Customer();
		} else if (name == "date") {
			return new Date();
		} else if (name == "supplier") {
			return new Supplier();
		} else if (name == "part") {
			return new Part();
		} else {
			return null;
		}
		
		
	}
	

}
