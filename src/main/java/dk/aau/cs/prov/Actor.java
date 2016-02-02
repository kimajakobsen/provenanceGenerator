package dk.aau.cs.prov;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;

import dk.aau.cs.main.Config;
import dk.aau.cs.main.Counter;

public abstract class Actor {
	Model model;
	Resource actorSubject ;
	
	public Actor (String name) {
		actorSubject = ResourceFactory.createResource(Config.getNamespace()+name+"/"+Counter.getCounter(name)+"/");
		model = ModelFactory.createDefaultModel();
	}

	public RDFNode getSubject() {
		return actorSubject;
	}

	public abstract Model createModel();
}
