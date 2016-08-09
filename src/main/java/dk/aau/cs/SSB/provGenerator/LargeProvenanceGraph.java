package dk.aau.cs.SSB.provGenerator;

import dk.aau.cs.SSB.provGenerator.ProvDataset.Customer;
import dk.aau.cs.SSB.provGenerator.ProvDataset.Date;
import dk.aau.cs.SSB.provGenerator.ProvDataset.Lineorder;
import dk.aau.cs.SSB.provGenerator.ProvDataset.Part;
import dk.aau.cs.SSB.provGenerator.ProvDataset.ProvDataset;
import dk.aau.cs.SSB.provGenerator.ProvDataset.Supplier;

public class LargeProvenanceGraph implements ProvenanceTripleGraphSize {

	public ProvDataset getProvenanceDataset(String identifierName) {
		if (identifierName == "lineorder") {
			return new Lineorder();
		} else if (identifierName == "customer") {
			return new Customer();
		} else if (identifierName == "date") {
			return new Date();
		} else if (identifierName == "supplier") {
			return new Supplier();
		} else if (identifierName == "part") {
			return new Part();
		} else {
			throw new IllegalArgumentException("unknown schema indentifier name "+identifierName);
		}
	}

}
