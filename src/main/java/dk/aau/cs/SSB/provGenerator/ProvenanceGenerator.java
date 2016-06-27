package dk.aau.cs.SSB.provGenerator;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;

import dk.aau.cs.SSB.provGenerator.ProvDataset.ProvDataset;
import dk.aau.cs.SSB.schema.Schema;

public abstract class ProvenanceGenerator {
	protected Schema schema;
	private ProvenanceTripleGraphSize graphSize;
	
	public ProvenanceGenerator(Schema schema, ProvenanceTripleGraphSize graphSize) {
		this.schema = schema;
		this.graphSize = graphSize;
	}

	public abstract Model getProvenanceTriples(String[] line) ;

	public abstract String getProvenanceIdentifier(Statement s) ;
	
	protected ProvDataset getProvenanceDataset(String identifierName) {
		return graphSize.getProvenanceDataset(identifierName);
	}
}
