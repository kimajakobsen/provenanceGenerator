package dk.aau.cs.helper;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;

public class QB4OLAP {
	

	//QB4OLAP v1.2
	public static Resource Version = ResourceFactory.createResource("http://purl.org/qb4olap/cubes_v1.2");
	
	//Namespaces
	public static String QBns = "http://purl.org/linked-data/cube#";
	public static String QB4OLAPns = "http://purl.org/qb4olap/cubes#";
	
	//types
	public static Resource DataStructureDefinition = ResourceFactory.createResource(QBns+"DataStructureDefinition");
	public static Resource DimensionProperty = ResourceFactory.createResource(QBns+"DimensionProperty");
	public static Resource HierarchyProperty = ResourceFactory.createResource(QBns+"HierarchyProperty");
	public static Resource Observation = ResourceFactory.createResource(QBns+"Observation");
	
	
	//properties
	public static Property hasHierarchy = ResourceFactory.createProperty(QB4OLAPns+"hasHierarchy");
	public static Property inHierarchy = ResourceFactory.createProperty(QB4OLAPns+"inHierarchy");
	public static Property inDimension = ResourceFactory.createProperty(QB4OLAPns+"inDimension");
	public static Property memberOf = ResourceFactory.createProperty(QB4OLAPns+"memberOf");
	public static Property dataSet = ResourceFactory.createProperty(QBns+"dataset");
	
	
}
