package dk.aau.cs.SSB.provGenerator.ProvDataset;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import dk.aau.cs.prov.Entity;
import dk.aau.cs.prov.Simple;

public class AllSimple extends ProvDataset {
	
	Model model;
	String provenanceIdentifier;
	
	public AllSimple() {
		model = ModelFactory.createDefaultModel();
		
		Entity simple1 = new Simple();
		
		provenanceIdentifier = simple1.getSubject().toString();
		
		model.add(simple1.createModel());
	}
	
	@Override
	public Model getProvenanceTriples() {
		return model;
	}

	@Override
	public String getProvenanceIdentifier() {
		return provenanceIdentifier;
	}
}
