package dk.aau.cs.SSB.provGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;

import dk.aau.cs.prov.Activity;
import dk.aau.cs.prov.Cleaner;
import dk.aau.cs.prov.Entity;
import dk.aau.cs.prov.EntitySet;
import dk.aau.cs.prov.Merge;
import dk.aau.cs.prov.Source;

public class Customer extends ProvGenerator {

	private ArrayList<String> sources = new ArrayList<String>();
	private ArrayList<String> cleaners = new ArrayList<String>();
	private ArrayList<String> actors = new ArrayList<String>();
	private ArrayList<String> mergers = new ArrayList<String>();
	
	Pair<LocalDateTime, LocalDateTime> level1 = IntervalManager.getIntervalLevel(1);
	Pair<LocalDateTime, LocalDateTime> level2 = IntervalManager.getIntervalLevel(2);
	Pair<LocalDateTime, LocalDateTime> level3 = IntervalManager.getIntervalLevel(3);
	
	public Customer() {
		sources.add("prDepartment");
		sources.add("accountingDepartment");
		//sources.add("Web form");
		sources.add("excelFile");
		//sources.add("Public register");
		//sources.add("Sales department");
		//sources.add("Database");
		
		cleaners.add("removeDoubleQuotes");
		cleaners.add("toLowerCase");
		cleaners.add("removeLocalizedCharecters");
		
		mergers.add("");
		mergers.add("");
		mergers.add("");
		
		
	}
	
	@Override
	public Model getProvenanceTriples(Statement s) {
		
		Model model = ModelFactory.createDefaultModel();
		//TODO add actor
		
		Entity source1 = new Source(sources.remove(0));
		Entity source2 = new Source(sources.remove(0));
		Entity source3 = new Source(sources.remove(0));
		
		Activity cleaner1 = new Cleaner(source1,cleaners.remove(0),level1);
		Entity entity1 = new EntitySet(cleaner1);
		Activity cleaner2 = new Cleaner(entity1,cleaners.remove(0),level2);
		Entity entity2 = new EntitySet(cleaner2);
		
		Activity cleaner3 = new Cleaner(source2,cleaners.remove(0),level3);
		Entity entity3 = new EntitySet(cleaner3);
		Activity merge1 = new Merge(entity3, source3);
		Entity entity4 = new EntitySet(merge1);
		
		Activity merge2 = new Merge(entity4, entity2);
		Entity entity5 = new EntitySet(merge2);
		
		model.add(source1.createModel());
		model.add(source2.createModel());
		model.add(source3.createModel());
		model.add(cleaner1.createModel());
		model.add(cleaner2.createModel());
		model.add(cleaner3.createModel());
		model.add(cleaner3.createModel());
		model.add(entity1.createModel());
		model.add(entity2.createModel());
		model.add(entity3.createModel());
		model.add(entity4.createModel());
		model.add(entity5.createModel());
		model.add(merge1.createModel());
		model.add(merge2.createModel());
		
		return model;
	}
}
