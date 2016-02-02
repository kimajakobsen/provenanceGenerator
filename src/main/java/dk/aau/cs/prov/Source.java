package dk.aau.cs.prov;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;

import dk.aau.cs.helper.PROV;

public class Source extends Entity {
	
	public Source(Actor actor) {
		super("source");
		this.actor = actor;
	}

	@Override
	public Model createModel() {
		Model model = ModelFactory.createDefaultModel();
		Resource subject = getSubject();
		
		model.add(subject,RDF.type,PROV.Entity);
		model.add(subject,PROV.wasAttributedTo,actor.getSubject());
		return model;
	}




}
