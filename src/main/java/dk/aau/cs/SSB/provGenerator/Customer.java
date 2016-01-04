package dk.aau.cs.SSB.provGenerator;

import java.time.LocalDateTime;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;

import dk.aau.cs.prov.Activity;
import dk.aau.cs.prov.Cleaner;
import dk.aau.cs.prov.Entity;
import dk.aau.cs.prov.IntemediateResults;
import dk.aau.cs.prov.Merge;
import dk.aau.cs.prov.Source;

public class Customer extends ProvGenerator {

//	private ArrayList<String> sources = new ArrayList<String>();
//	private ArrayList<String> cleaners = new ArrayList<String>();
//	private ArrayList<String> actors = new ArrayList<String>();
//	private ArrayList<String> mergers = new ArrayList<String>();
	
	Pair<LocalDateTime, LocalDateTime> level1 = IntervalManager.getIntervalLevel(1);
	Pair<LocalDateTime, LocalDateTime> level2 = IntervalManager.getIntervalLevel(2);
	Pair<LocalDateTime, LocalDateTime> level3 = IntervalManager.getIntervalLevel(3);
	
	public Customer() {
//		sources.add("prDepartment");
//		sources.add("accountingDepartment");
//		sources.add("Web form");
//		sources.add("excelFile");
//		sources.add("Public register");O
//		sources.add("Sales department");
//		sources.add("Database");
//		
//		cleaners.add("removeDoubleQuotes");
//		cleaners.add("toLowerCase");
//		cleaners.add("removeLocalizedCharecters");
//		
//		mergers.add("");
//		mergers.add("");
//		mergers.add("");
	}
	
	@Override
	public Model getProvenanceTriples(Statement s) {
		
		Model model = ModelFactory.createDefaultModel();
		//TODO add actor
		
		Entity source1 = new Source();
		Entity source2 = new Source();
		Entity source3 = new Source();
		
		Activity cleaner1 = new Cleaner(source1,level1);
		Entity entity1 = new IntemediateResults(cleaner1);
		Activity cleaner2 = new Cleaner(entity1,level2);
		Entity entity2 = new IntemediateResults(cleaner2);
		
		Activity cleaner3 = new Cleaner(source2,level1);
		Entity entity3 = new IntemediateResults(cleaner3);
		Activity merge1 = new Merge(entity3, source3,level2);
		Entity entity4 = new IntemediateResults(merge1);
		
		Activity merge2 = new Merge(entity4, entity2,level3);
		Entity entity5 = new IntemediateResults(merge2);
		
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
