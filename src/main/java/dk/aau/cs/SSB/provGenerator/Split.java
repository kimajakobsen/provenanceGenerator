package dk.aau.cs.SSB.provGenerator;

import java.util.HashMap;
import java.util.Map;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;

import dk.aau.cs.SSB.provGenerator.ProvDataset.ProvDataset;
import dk.aau.cs.SSB.schema.Schema;

public class Split extends ProvenanceGenerator {
	
	private int attributeIndex;
	private Map<String, Model> attributeToModel = new HashMap<String, Model>();
	private Map<String, String> subjectToAttribute = new HashMap<String, String>();
	
	public Split(Schema schema, int attribute) {
		super(schema);
		this.schema = schema;
		this.attributeIndex = attribute;
	}

	@Override
	public Model getProvenanceTriples(String[] rawLine) {
		String attribute = rawLine[attributeIndex];
		ProvDataset provdataset = getProvenanceDataset(schema.getIdentifierName());
		if (!attributeToModel.containsKey(attribute)) {
			Model model = provdataset.getProvenanceTriples();
			attributeToModel.put(attribute, model);
			subjectToAttribute.put(schema.getStringIRI(rawLine), provdataset.getProvenanceIdentifier());
			//System.out.println("Adding the Subject: "+ schema.getStringIRI(rawLine)+" with Attribute "+ attribute +" have the prov identifier: " + provdataset.getProvenanceIdentifier());
		} else {
			//System.out.println("The Subject: "+ schema.getStringIRI(rawLine)+" with Attribute "+ attribute +" exists");
			subjectToAttribute.put(schema.getStringIRI(rawLine), provdataset.getProvenanceIdentifier());
		}
		return attributeToModel.get(attribute);
	}

	@Override
	public String getProvenanceIdentifier(Statement s) {
		if (!subjectToAttribute.containsKey(s.getSubject().toString())) {
			System.out.println(subjectToAttribute.entrySet());
			throw new IllegalStateException("Asking for a graph that does not exist in the list above: " + s.getSubject());
		}
		return subjectToAttribute.get(s.getSubject().toString());
	}

}
