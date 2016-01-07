package dk.aau.cs.prov;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;

import dk.aau.cs.helper.PROV;

public class ProvenanceEntity extends Entity {
	Resource sourceActivityName;
	Activity sourceActivity;

	public ProvenanceEntity(Activity cleaner1) {
		super("entity");
		sourceActivityName = cleaner1.getSubject();
		cleaner1.setGeneratedData(this);
		sourceActivity = cleaner1;
	}

	@Override
	public Model createModel() {
		Model model = ModelFactory.createDefaultModel();
		
		Resource subject = getSubject();
		
		model.add(subject,RDF.type,PROV.Entity);
		model.add(subject,PROV.wasGeneratedBy,sourceActivityName);
		model.add(subject,PROV.generatedAtTime,sourceActivity.getFinishTime());
		
		return model;
	}





}
