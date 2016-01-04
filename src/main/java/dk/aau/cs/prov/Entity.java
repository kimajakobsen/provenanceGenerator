package dk.aau.cs.prov;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

public abstract class Entity {
	
	public abstract Resource getURI();
	
	public abstract Model createModel();
}
