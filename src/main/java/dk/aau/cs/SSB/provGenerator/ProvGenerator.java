package dk.aau.cs.SSB.provGenerator;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;

public abstract class ProvGenerator {
	String nameSpace;
	
	
	
	public abstract Model getProvenanceTriples(Statement s);
	
	protected Statement createTriple(String s, String p, String o) {
		// TODO Auto-generated method stub
		return null;
		
	}

	public String getSubjectOfInformationEntityResource() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
