package dk.aau.cs.SSB.provGenerator;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;

import dk.aau.cs.SSB.provGenerator.ProvDatasetLarge.Customer;
import dk.aau.cs.SSB.provGenerator.ProvDatasetLarge.Date;
import dk.aau.cs.SSB.provGenerator.ProvDatasetLarge.Lineorder;
import dk.aau.cs.SSB.provGenerator.ProvDatasetLarge.Part;
import dk.aau.cs.SSB.provGenerator.ProvDatasetLarge.ProvDataset;
import dk.aau.cs.SSB.provGenerator.ProvDatasetLarge.Supplier;
import dk.aau.cs.SSB.schema.Schema;

public abstract class ProvenanceGenerator {
	protected Schema schema;
	
	public ProvenanceGenerator(Schema schema) {
		this.schema = schema;
	}

	public abstract Model getProvenanceTriples(String[] line) ;

	public abstract String getProvenanceIdentifier(Statement s) ;
	
	protected ProvDataset getProvenanceDataset(String identifierName) {
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
