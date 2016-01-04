package dk.aau.cs.prov;

import java.time.LocalDateTime;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;

import dk.aau.cs.helper.PROV;

public class Merge extends Activity {
	Model model;
	Entity source1;
	Entity source2;
	

	public Merge(Entity source1, Entity source2, Pair<LocalDateTime, LocalDateTime> level2) {
		super("merge");
		this.source1 = source1;
		this.source2 = source2;
		
		model = ModelFactory.createDefaultModel();
	}

	@Override
	public Model createModel() {
		model.add(activitySubject,RDF.type,PROV.Entity); 
		model.add(activitySubject,PROV.used,source1.getSubject());
		model.add(activitySubject,PROV.used,source2.getSubject());
		model.add(activitySubject,PROV.startedAtTime,getStartTime());
		model.add(activitySubject,PROV.endedAtTime,getFinishTime());
		
		return model;
	}


	@Override
	public void setGeneratedData(Entity output) {
		// TODO Auto-generated method stub
		
	}


}
