package dk.aau.cs.prov;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.RDF;

import dk.aau.cs.helper.PROV;

public class SoftwareAgent extends Actor {

	public SoftwareAgent() {
		super("SoftwareAgent");
	}

	@Override
	public Model createModel() {
		model.add(actorSubject,RDF.type,PROV.Agent); 
		model.add(actorSubject,RDF.type,PROV.SoftwareAgent); 
		model.add(actorSubject,FOAF.name,"C3PO"); 
		return model;
	}

}
