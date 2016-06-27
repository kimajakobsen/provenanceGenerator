package dk.aau.cs.prov;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;

import dk.aau.cs.helper.PROV;

public class Simple extends Entity {

	public Simple() {
		super("simple");
	}

	@Override
	public Model createModel() {
		Model model = ModelFactory.createDefaultModel();
		Resource subject = getSubject();
		
		model.add(subject,RDF.type,PROV.Entity);
		return model;
	}
}
