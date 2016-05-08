package dk.aau.cs.SSB.provGenerator;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;

import dk.aau.cs.SSB.provGenerator.ProvDataset.ProvDataset;
import dk.aau.cs.SSB.schema.Schema;

public class Lowest extends ProvenanceGenerator {
	private String provenanceIndentifier;

	public Lowest(Schema schema) {
		super(schema);
	}

	@Override
	public String getProvenanceIdentifier(Statement s) {
		return provenanceIndentifier;
	}

	@Override
	public Model getProvenanceTriples(String[] line) {
		ProvDataset provdataset = getProvenanceDataset(schema.getIdentifierName());
		provenanceIndentifier = provdataset.getProvenanceIdentifier();
		return provdataset.getProvenanceTriples();
	}
}
