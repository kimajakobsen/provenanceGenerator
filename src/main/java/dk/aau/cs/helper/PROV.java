package dk.aau.cs.helper;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;

public class PROV {
	//Namespaces
	public static String provNS = "http://www.w3.org/ns/prov#";
	
	//Prov types
	public static Resource Entity = ResourceFactory.createResource(provNS+"Entity");
	public static Resource Activity = ResourceFactory.createResource(provNS+"Activity");
	public static Resource Agent = ResourceFactory.createResource(provNS+"Agent");
	public static Resource Bundle = ResourceFactory.createResource(provNS+"Bundle");
	public static Resource SoftwareAgent = ResourceFactory.createResource(provNS+"SoftwareAgent");
	
	//prov properties
	public static Property wasAttributedTo = ResourceFactory.createProperty(PROV.provNS,"wasAttributedTo");
	public static Property used = ResourceFactory.createProperty(PROV.provNS,"used");
	public static Property generated = ResourceFactory.createProperty(PROV.provNS,"generated");
	public static Property startedAtTime = ResourceFactory.createProperty(PROV.provNS,"startedAtTime");
	public static Property endedAtTime = ResourceFactory.createProperty(PROV.provNS,"endedAtTime");
	public static Property wasGeneratedBy = ResourceFactory.createProperty(PROV.provNS,"wasGeneratedBy");
	public static Property generatedAtTime = ResourceFactory.createProperty(PROV.provNS,"generatedAtTime");
	
	
}
