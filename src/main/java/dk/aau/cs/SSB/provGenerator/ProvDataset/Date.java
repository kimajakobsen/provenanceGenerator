package dk.aau.cs.SSB.provGenerator;

import java.time.LocalDateTime;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;

import dk.aau.cs.prov.Activity;
import dk.aau.cs.prov.Actor;
import dk.aau.cs.prov.Cleaner;
import dk.aau.cs.prov.Entity;
import dk.aau.cs.prov.Organization;
import dk.aau.cs.prov.Person;
import dk.aau.cs.prov.ProvenanceEntity;
import dk.aau.cs.prov.SoftwareAgent;
import dk.aau.cs.prov.Source;

public class Date extends ProvGenerator {
	
	Pair<LocalDateTime, LocalDateTime> level1 = IntervalManager.getIntervalLevel(1);
	Pair<LocalDateTime, LocalDateTime> level2 = IntervalManager.getIntervalLevel(2);
	Pair<LocalDateTime, LocalDateTime> level3 = IntervalManager.getIntervalLevel(3);
	Pair<LocalDateTime, LocalDateTime> level4 = IntervalManager.getIntervalLevel(4);
	Pair<LocalDateTime, LocalDateTime> level5 = IntervalManager.getIntervalLevel(5);
	Model model;
	String provenanceIdentifier;
	
	public Date() {
		model = ModelFactory.createDefaultModel();
		
		Actor actor1 = new Person();
		Actor actor4 = new Organization();
		Actor actor5 = new SoftwareAgent();
		Actor actor6 = new SoftwareAgent();
		
		Entity source1 = new Source(actor4);
		
		Activity cleaner1 = new Cleaner(source1,level1,actor1);
		Entity entity1 = new ProvenanceEntity(cleaner1);
		Activity cleaner2 = new Cleaner(entity1,level2,actor5);
		Entity entity2 = new ProvenanceEntity(cleaner2);
		Activity cleaner3 = new Cleaner(entity1,level3,actor6);
		Entity entity3 = new ProvenanceEntity(cleaner3);
		Activity cleaner4 = new Cleaner(entity1,level4,actor6);
		Entity entity4 = new ProvenanceEntity(cleaner4);
		Activity cleaner5 = new Cleaner(entity1,level5,actor5);
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
		model.add(actor4.createModel());
		model.add(actor5.createModel());
		model.add(actor6.createModel());
	}
	
	@Override
	public Model getProvenanceTriples(Statement s) {
		return model;
	}

	@Override
	public String getProvenanceIdentifier() {
		return provenanceIdentifier;
	}
}
