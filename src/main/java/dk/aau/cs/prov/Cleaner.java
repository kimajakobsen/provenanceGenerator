package dk.aau.cs.prov;

import java.time.LocalDateTime;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.vocabulary.RDF;

import dk.aau.cs.helper.PROV;

public class Cleaner extends Activity {
	Entity source;
	String name;
	RDFNode actorSubject;

	public Cleaner(Entity source1, Pair<LocalDateTime, LocalDateTime> pair, Actor actor) {
		super("cleaner");
		source = source1;
		timeInteval = pair;
		actorSubject = actor.getSubject();
	}

	@Override
	public Model createModel() {
		model.add(activitySubject,RDF.type,PROV.Activity); 
		model.add(activitySubject,PROV.used,source.getSubject());
		model.add(activitySubject,PROV.startedAtTime,getStartTime());
		model.add(activitySubject,PROV.endedAtTime,getFinishTime());
		model.add(activitySubject,PROV.wasAttributedTo,actorSubject);
		return model;
	}
}
