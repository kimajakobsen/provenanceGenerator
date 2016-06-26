package dk.aau.cs.SSB.provGenerator.ProvDatasetLarge;

import java.time.LocalDateTime;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import dk.aau.cs.prov.Activity;
import dk.aau.cs.prov.Actor;
import dk.aau.cs.prov.Cleaner;
import dk.aau.cs.prov.Entity;
import dk.aau.cs.prov.Merge;
import dk.aau.cs.prov.Organization;
import dk.aau.cs.prov.Person;
import dk.aau.cs.prov.ProvenanceEntity;
import dk.aau.cs.prov.Source;

public class Supplier extends ProvDataset {
	
	Pair<LocalDateTime, LocalDateTime> level1 = IntervalContainer.getIntervalLevel(1);
	Pair<LocalDateTime, LocalDateTime> level2 = IntervalContainer.getIntervalLevel(2);
	Pair<LocalDateTime, LocalDateTime> level3 = IntervalContainer.getIntervalLevel(3);
	Model model;
	String provenanceIdentifier;
	
	public Supplier() {
		model = ModelFactory.createDefaultModel();
		
		Actor actor1 = new Person();
		Actor actor2 = new Person();
		Actor actor3 = new Person();
		Actor actor4 = new Person();
		Actor actor5 = new Organization();
		Actor actor6 = new Organization();
		Actor actor7 = new Organization();
		
		Entity source1 = new Source(actor5);
		Entity source2 = new Source(actor5);
		Entity source3 = new Source(actor6);
		Entity source4 = new Source(actor7);
		
		Activity merge1 = new Merge(source1, source2,level1,actor1);
		Activity merge2 = new Merge(source3, source4,level1,actor2);
		Entity entity1 = new ProvenanceEntity(merge1);
		Entity entity2 = new ProvenanceEntity(merge2);
		
		Activity merge3 = new Merge(entity1, entity2,level2,actor3);
		Entity entity3 = new ProvenanceEntity(merge3);
		
		Activity cleaner1 = new Cleaner(entity3,level3,actor4);
		Entity entity4 = new ProvenanceEntity(cleaner1);
		
		provenanceIdentifier = entity4.getSubject().toString();
		
		model.add(source1.createModel());
		model.add(source2.createModel());
		model.add(source3.createModel());
		model.add(source4.createModel());
		model.add(cleaner1.createModel());
		model.add(entity1.createModel());
		model.add(entity2.createModel());
		model.add(entity3.createModel());
		model.add(entity4.createModel());
		model.add(merge1.createModel());
		model.add(merge2.createModel());
		model.add(merge3.createModel());
		model.add(actor1.createModel());
		model.add(actor2.createModel());
		model.add(actor3.createModel());
		model.add(actor4.createModel());
		model.add(actor5.createModel());
		model.add(actor6.createModel());
		model.add(actor7.createModel());
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
