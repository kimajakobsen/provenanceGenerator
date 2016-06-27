package dk.aau.cs.SSB.provGenerator;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;

import dk.aau.cs.SSB.provGenerator.ProvDataset.ProvDataset;
import dk.aau.cs.SSB.schema.Schema;

public class Lowest extends ProvenanceGenerator {
	private ProvDataset provdataset;
	private String provenanceIndentifier = "";

	public Lowest(ProvenanceTripleGraphSize graphSize, Schema schema) {
		super(schema, graphSize);
	}

	@Override
	public String getProvenanceIdentifier(Statement s) {
		return provenanceIndentifier;
	}

	@Override
	public Model getProvenanceTriples(String[] line) {
		if (provenanceIndentifier.isEmpty()) {
			provdataset = getProvenanceDataset(schema.getIdentifierName());
			provenanceIndentifier = provdataset.getProvenanceIdentifier();
		}
		
		return provdataset.getProvenanceTriples();
	}
}
