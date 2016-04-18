package dk.aau.cs.SSB.schema;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.TimeZone;

import javax.management.InvalidAttributeValueException;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.shared.ConfigException;

import dk.aau.cs.main.Config;

public abstract class Schema {
	
	protected HashMap<Integer, SchemaPropertySet> schema = new HashMap<Integer, SchemaPropertySet>();
	protected String identifierName;
	private String namespace = Config.getNamespace();
	
	protected Schema(String identifierName) {
		this.identifierName = identifierName;
	}


	public Resource getIRI(String[] line) {
		String name = namespace+identifierName;
		for (Entry<Integer, SchemaPropertySet> iterable_element : schema.entrySet()) {
			if (iterable_element.getValue().isPartOfKey()) {
				name += line[iterable_element.getKey()];  
				name += "_";
			}
		}
		return ResourceFactory.createResource(name.substring(0, name.length()-1)+"/");
	}

	public Property getProperty(int index) {
		return ResourceFactory.createProperty(namespace,schema.get(index).getName());
	}


	
	public Literal createLiteralWithType(int index, String input) throws InvalidAttributeValueException, ParseException  {
		String type = schema.get(index).getType();
		Literal literal;
		switch (type) {
		case "int":
			literal = ResourceFactory.createTypedLiteral(new Integer(input));
			break;
		case "double":
			literal = ResourceFactory.createTypedLiteral(new Double(input));
			break;
		case "bool":
			boolean bool;
			if (input.equals("1")) {
				bool = true;
			} else {
				bool = false;
			}
			literal = ResourceFactory.createTypedLiteral(new Boolean(bool));
			break;
		case "str":
			literal = ResourceFactory.createLangLiteral(input, "en");
			break;
		case "date":
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("MMMMM dd, yyyy", Locale.getDefault());
			
			cal.setTime(sdf.parse(input));// all done
			cal = convertToGmt(cal);
			literal = ResourceFactory.createTypedLiteral(cal);
			break;
		case "other":
			literal = ResourceFactory.createPlainLiteral(input);
			break;
		default:
			throw new InvalidAttributeValueException("The type: "+type+ "is not a valid type, please use int,double,str, boolean, date, or other");
		}
		return literal;
	}
	
	private static Calendar convertToGmt(Calendar cal) {

		Date date = cal.getTime();
		TimeZone tz = cal.getTimeZone();

		//Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT 
		long msFromEpochGmt = date.getTime();

		//gives you the current offset in ms from GMT at the current date
		int offsetFromUTC = tz.getOffset(msFromEpochGmt);

		//create a new calendar in GMT timezone, set to this date and add the offset
		Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		gmtCal.setTime(date);
		gmtCal.add(Calendar.MILLISECOND, offsetFromUTC);

		return gmtCal;
	}

	public String getTripleGraphName(int tripleID) throws ConfigException {
		return Config.getUniqueProvenanceGraph();
	}

	public String getProvenanceGraphName() {
		return Config.getProvenanceNamespace();
	}
	
	public String getIdentifierName() {
		return identifierName;
	}

	
	public HashMap<Integer, SchemaPropertySet> getSchema() {
		return schema;
	}
	
	public int size() {
		return schema.size();
	}

	public abstract Model getCubeInstanceMetadataTriples(Resource subject);

	public Boolean isForeignKeyToLevelMember(int schemaPropertyIndex) {
		if (!schema.get(schemaPropertyIndex).equals("")) {
			return true;
		} 
		return false;
	}
	
	public String getObjectPropertyName(int index) {
		return schema.get(index).getObjectPropertyName();
	}

	public Property getLevelProperty(int schemaPropertyIndex) {
		Property predicate = ResourceFactory.createProperty(Config.getNamespace()+getObjectPropertyName(schemaPropertyIndex));
		return predicate;
	}

	
}
