package dk.aau.cs.helper;

import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;

public class FOAF {
	//Namespaces
	public static String foafNS = "http://xmlns.com/foaf/0.1/";
	
	//foaf stuff
	public static Resource Organization = ResourceFactory.createResource(foafNS+"Organization");
	public static Resource Person = ResourceFactory.createResource(foafNS+"Person");
}
