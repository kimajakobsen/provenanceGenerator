package dk.aau.cs.prov;

import java.time.LocalDateTime;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;

import dk.aau.cs.main.Config;
import dk.aau.cs.main.Counter;

public abstract class Activity extends Node {
	Pair<LocalDateTime,LocalDateTime> timeInteval;
	Resource activitySubject;
	
	public Activity(String name){
		activitySubject = ResourceFactory.createResource(Config.getNamespace()+name+"/"+Counter.getCounter(name)+"/");
	}
	
	@Override
	public Resource getSubject() {
		return activitySubject;
	}
	
	public abstract Model createModel();
	
	public abstract void setGeneratedData(Entity output);
	
	public RDFNode getFinishTime() {
		return ResourceFactory.createTypedLiteral(timeInteval.getRight().toString(), XSDDatatype.XSDdate);
	}
	
	public RDFNode getStartTime() {
		return ResourceFactory.createTypedLiteral(timeInteval.getLeft().toString(), XSDDatatype.XSDdate);
	}
}
