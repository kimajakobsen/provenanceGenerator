package dk.aau.cs.prov;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;

import dk.aau.cs.helper.PROV;
import dk.aau.cs.main.Config;
import dk.aau.cs.main.Counter;

public class EntitySet extends Entity {
	Resource sourceActivityName;
	Activity sourceActivity;

	public EntitySet(Activity cleaner1) {
		sourceActivityName = cleaner1.getSubject();
		cleaner1.setGeneratedData(this);
		sourceActivity = cleaner1;
	}

	@Override
	public Resource getURI() {
		return ResourceFactory.createResource(Config.getNamespace()+"entity/"+Counter.getCounter("entity")+"/");
	}

	@Override
	public Model createModel() {
		Model model = ModelFactory.createDefaultModel();
		
		Resource subject = getURI();
		
		model.add(subject,RDF.type,PROV.Entity);
		model.add(subject,PROV.wasGeneratedBy,sourceActivityName);
		model.add(subject,PROV.generatedAtTime,sourceActivity.getFinishTime());
		
		return model;
	}

}
