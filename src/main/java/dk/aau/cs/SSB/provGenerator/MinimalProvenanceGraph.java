package dk.aau.cs.SSB.provGenerator;

import dk.aau.cs.SSB.provGenerator.ProvDataset.AllSimple;
import dk.aau.cs.SSB.provGenerator.ProvDataset.ProvDataset;

public class MinimalProvenanceGraph implements ProvenanceTripleGraphSize {

	public ProvDataset getProvenanceDataset(String identifierName) {
		if (identifierName == "lineorder") {
			return new AllSimple();
		} else if (identifierName == "customer") {
			return new AllSimple();
		} else if (identifierName == "date") {
			return new AllSimple();
		} else if (identifierName == "supplier") {
			return new AllSimple();
		} else if (identifierName == "part") {
			return new AllSimple();
		} else {
			throw new IllegalArgumentException("unknown schema indentifier name "+identifierName);
		}
	}
}
