package dk.aau.cs.prov;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.RDF;

import dk.aau.cs.helper.PROV;

public class Organization extends Actor {

	public Organization() {	
		super("Organization");
	}

	@Override
	public Model createModel() {
		model.add(actorSubject,RDF.type,PROV.Agent); 
		model.add(actorSubject,RDF.type,FOAF.Organization); 
		model.add(actorSubject,FOAF.name,"The Empire"); 
		return model;
	}

}
