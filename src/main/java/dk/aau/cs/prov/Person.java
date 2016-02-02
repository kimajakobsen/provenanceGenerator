package dk.aau.cs.prov;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.RDF;

import dk.aau.cs.helper.PROV;

public class Person extends Actor {

	public Person() {
		super("Person");
	}

	@Override
	public Model createModel() {
		model.add(actorSubject,RDF.type,PROV.Agent); 
		model.add(actorSubject,RDF.type,FOAF.Person); 
		model.add(actorSubject,FOAF.givenname,"Darth Vader"); 
		model.add(actorSubject,FOAF.mbox,"DarthVader@deathstar.org"); 
		return model;
	}
}
