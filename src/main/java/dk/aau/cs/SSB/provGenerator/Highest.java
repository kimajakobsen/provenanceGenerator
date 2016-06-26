package dk.aau.cs.SSB.provGenerator;

import java.util.HashMap;
import java.util.Map;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

import dk.aau.cs.SSB.provGenerator.ProvDatasetLarge.ProvDataset;
import dk.aau.cs.SSB.schema.Schema;


public class Highest extends ProvenanceGenerator {
	private Map<String, String> subjectToAttribute = new HashMap<String, String>();

	public Highest(Schema schema) {
		super(schema);
	}

	@Override
	public String getProvenanceIdentifier(Statement s) {
		return subjectToAttribute.get(s.getSubject().toString()+s.getPredicate().toString());
	}

	@Override
	public Model getProvenanceTriples(String[] line) {
		Model model = ModelFactory.createDefaultModel();
		for (int i=0; i<line.length; i++) {
			ProvDataset provdataset = getProvenanceDataset(schema.getIdentifierName());
			model.add(provdataset.getProvenanceTriples());
			Resource subject = schema.getIRI(line);
			Property predicate = schema.getProperty(i);
			subjectToAttribute.put(subject.toString()+predicate.toString(), provdataset.getProvenanceIdentifier());
		}
		return model;
	}
}
