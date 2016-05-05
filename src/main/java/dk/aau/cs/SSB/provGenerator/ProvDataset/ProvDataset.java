package dk.aau.cs.SSB.provGenerator.ProvDataset;

import org.apache.jena.rdf.model.Model;

public abstract class ProvDataset {
	String nameSpace;
	
	public abstract String getProvenanceIdentifier();
	
	public abstract Model getProvenanceTriples();
}
