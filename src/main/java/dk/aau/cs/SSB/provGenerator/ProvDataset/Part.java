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
import dk.aau.cs.prov.Merge;
import dk.aau.cs.prov.Organization;
import dk.aau.cs.prov.Person;
import dk.aau.cs.prov.ProvenanceEntity;
import dk.aau.cs.prov.SoftwareAgent;
import dk.aau.cs.prov.Source;

public class Part extends ProvGenerator {
	
	Pair<LocalDateTime, LocalDateTime> level1 = IntervalManager.getIntervalLevel(1);
	Pair<LocalDateTime, LocalDateTime> level2 = IntervalManager.getIntervalLevel(2);
	Model model;
	String provenanceIdentifier;
	
	public Part() {
		model = ModelFactory.createDefaultModel();
		
		Actor actor1 = new Person();
		Actor actor2 = new Organization();
		Actor actor3 = new SoftwareAgent();
		
		Entity source1 = new Source(actor2);
		Entity source2 = new Source(actor2);
		
		Activity cleaner1 = new Cleaner(source1,level1,actor3);
		Entity entity1 = new ProvenanceEntity(cleaner1);
		Activity cleaner2 = new Cleaner(source2,level1,actor3);
		Entity entity2 = new ProvenanceEntity(cleaner2);
		
		Activity merge1 = new Merge(entity1, entity2,level2,actor1);
		Entity entity3 = new ProvenanceEntity(merge1);
		
		provenanceIdentifier = entity3.getSubject().toString();
		
		model.add(source1.createModel());
		model.add(source2.createModel());
		model.add(cleaner1.createModel());
		model.add(cleaner2.createModel());
		model.add(entity1.createModel());
		model.add(entity2.createModel());
		model.add(entity3.createModel());
		model.add(merge1.createModel());
		model.add(actor1.createModel());
		model.add(actor2.createModel());
		model.add(actor3.createModel());
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
