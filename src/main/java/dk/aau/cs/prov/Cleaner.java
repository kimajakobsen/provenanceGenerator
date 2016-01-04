package dk.aau.cs.prov;

import java.time.LocalDateTime;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;

import dk.aau.cs.helper.PROV;
import dk.aau.cs.main.Config;
import dk.aau.cs.main.Counter;

public class Cleaner extends Activity {
	Entity source;
	
	String name;
	Resource activitySubject;
	Model model;
	//int maxDuration;
	

	public Cleaner(Entity source1, String name, Pair<LocalDateTime, LocalDateTime> pair) {
		source = source1;
		timeInteval = pair;
		this.name = name;
		
		model = ModelFactory.createDefaultModel();
		activitySubject = ResourceFactory.createResource(Config.getNamespace()+"filter/"+Counter.getCounter("filter")+"/");
		//maxDuration = timeInteval.getRight().compareTo(timeInteval.getLeft());
	}

	@Override
	public void setGeneratedData(Entity output) {
		model.add(activitySubject,PROV.generated,output.getURI());
	}

	@Override
	public Model createModel() {
		model.add(activitySubject,RDF.type,PROV.Entity); 
		model.add(activitySubject,PROV.used,source.getURI());
		model.add(activitySubject,PROV.startedAtTime,getStartTime());
		model.add(activitySubject,PROV.endedAtTime,getFinishTime());
		
		return model;
	}

	@Override
	public Resource getSubject() {
		return activitySubject;
	}
}
