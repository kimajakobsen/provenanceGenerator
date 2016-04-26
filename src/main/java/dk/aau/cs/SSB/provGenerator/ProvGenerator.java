package dk.aau.cs.SSB.provGenerator;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;

public abstract class ProvGenerator {
	String nameSpace;
	
	public abstract String getProvenanceIdentifier();
	
	public abstract Model getProvenanceTriples(Statement s);
}
