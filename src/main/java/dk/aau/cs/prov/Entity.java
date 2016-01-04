package dk.aau.cs.prov;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;

import dk.aau.cs.main.Config;
import dk.aau.cs.main.Counter;

public abstract class Entity extends Node {
	Resource entitySubject;
	
	Entity(String name) {
		entitySubject = ResourceFactory.createResource(Config.getNamespace()+name+"/"+Counter.getCounter(name)+"/");
	}
	
	@Override
	public Resource getSubject() {
		return entitySubject;
	}
	
	public abstract Model createModel();
}
