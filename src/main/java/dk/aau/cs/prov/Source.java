package dk.aau.cs.prov;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;

import dk.aau.cs.helper.PROV;

public class Source extends Entity {
	
	public Source() {
		super("source");
	}

	@Override
	public Model createModel() {
		Model model = ModelFactory.createDefaultModel();
		Resource subject = getSubject();
		
		model.add(subject,RDF.type,PROV.Entity);
		//TODO Attributed to skal være en agent. se prov o
		//model.add(subject,PROV.wasAttributedTo,SOME ACTOR);
		
		return model;
	}

	@Override
	public Resource getSubject() {
		// TODO Auto-generated method stub
		return null;
	}
}
