package dk.aau.cs.prov;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;

import dk.aau.cs.helper.PROV;
import dk.aau.cs.main.Config;

public class Source extends Entity {
	protected String identifier;
	
	public Source(String name) {
		identifier = name;
	}

	@Override
	public Model createModel() {
		Model model = ModelFactory.createDefaultModel();
		
		Resource subject = getURI();
		
		model.add(subject,RDF.type,PROV.Entity);
		//TODO Attributed to skal være en agent. se prov o
		model.add(subject,PROV.wasAttributedTo,ResourceFactory.createResource(PROV.provNS+identifier));
		
		return model;
	}

	@Override
	public Resource getURI() {
		return ResourceFactory.createResource(Config.getNamespace()+"source/"+identifier+"/");
	}
}
