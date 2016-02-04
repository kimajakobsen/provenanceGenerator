package dk.aau.cs.prov;

import java.time.LocalDateTime;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.vocabulary.RDF;

import dk.aau.cs.helper.PROV;

public class Merge extends Activity {
	Entity source1;
	Entity source2;
	RDFNode actorSubject;
	

	public Merge(Entity source1, Entity source2, Pair<LocalDateTime, LocalDateTime> pair, Actor actor) {
		super("merge");
		this.source1 = source1;
		this.source2 = source2;
		timeInteval = pair;
		actorSubject = actor.getSubject();
		
	}

	@Override
	public Model createModel() {
		model.add(activitySubject,RDF.type,PROV.Entity); 
		model.add(activitySubject,PROV.used,source1.getSubject());
		model.add(activitySubject,PROV.used,source2.getSubject());
		model.add(activitySubject,PROV.startedAtTime,getStartTime());
		model.add(activitySubject,PROV.endedAtTime,getFinishTime());
		model.add(activitySubject,PROV.wasAttributedTo,actorSubject);
		
		return model;
	}
}
