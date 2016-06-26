package dk.aau.cs.SSB.provGenerator.ProvDatasetLarge;

import java.time.LocalDateTime;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import dk.aau.cs.prov.Activity;
import dk.aau.cs.prov.Actor;
import dk.aau.cs.prov.Cleaner;
import dk.aau.cs.prov.Entity;
import dk.aau.cs.prov.Organization;
import dk.aau.cs.prov.ProvenanceEntity;
import dk.aau.cs.prov.Source;



public class Lineorder extends ProvDataset {

	Pair<LocalDateTime, LocalDateTime> level1 = IntervalContainer.getIntervalLevel(1);
	Pair<LocalDateTime, LocalDateTime> level2 = IntervalContainer.getIntervalLevel(2);
	Pair<LocalDateTime, LocalDateTime> level3 = IntervalContainer.getIntervalLevel(3);
	Pair<LocalDateTime, LocalDateTime> level4 = IntervalContainer.getIntervalLevel(4);
	Pair<LocalDateTime, LocalDateTime> level5 = IntervalContainer.getIntervalLevel(5);
	Model model;
	String provenanceIdentifier;
	
	public Lineorder() {
		model = ModelFactory.createDefaultModel();
		
		Actor actor1 = new Organization();
		Entity source1 = new Source(actor1);
		
		Activity cleaner1 = new Cleaner(source1,level1,actor1);
		Entity entity1 = new ProvenanceEntity(cleaner1);
		Activity cleaner2 = new Cleaner(entity1,level2,actor1);
		Entity entity2 = new ProvenanceEntity(cleaner2);
		Activity cleaner3 = new Cleaner(entity2,level3,actor1);
		Entity entity3 = new ProvenanceEntity(cleaner3);
		Activity cleaner4 = new Cleaner(entity3,level4,actor1);
		Entity entity4 = new ProvenanceEntity(cleaner4);
		Activity cleaner5 = new Cleaner(entity4,level5,actor1);
		Entity entity5 = new ProvenanceEntity(cleaner5);
		provenanceIdentifier = entity5.getSubject().toString();
		
		model.add(source1.createModel());
		model.add(cleaner1.createModel());
		model.add(cleaner2.createModel());
		model.add(cleaner3.createModel());
		model.add(cleaner4.createModel());
		model.add(cleaner5.createModel());
		model.add(entity1.createModel());
		model.add(entity2.createModel());
		model.add(entity3.createModel());
		model.add(entity4.createModel());
		model.add(entity5.createModel());
		model.add(actor1.createModel());
	}
	
	@Override
	public Model getProvenanceTriples() {
		return model;
	}

	@Override
	public String getProvenanceIdentifier() {
		return provenanceIdentifier;
	}

}
