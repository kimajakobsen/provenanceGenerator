package dk.aau.cs.SSB.schema;

import javax.naming.InvalidNameException;

public class SchemaBuilder {

	public Schema build(String file) throws InvalidNameException {
		
		file = getFileNameWithoutExtension(file).toLowerCase();
		
		if (file.equals("customer")) {
			return new Customer();
		} else if (file.equals("supplier")) {
			return new Supplier();
		} else if (file.equals("lineorder")) {
			return new Lineorder();
		} else if (file.equals("part")) {
			return new Part();
		} else if (file.equals("date")) {
			return new Date();
		} else {
			throw new InvalidNameException("the schema "+file+" is not known. Please use customer, supplier, lineorder, part, or date.");
		}
		
		
	}
	
	private String getFileNameWithoutExtension(String csvFile2) {
		
		String[] folderSplit = csvFile2.split("/");
		String filename = folderSplit[folderSplit.length-1];
		String[] extensionSplit = filename.split("\\.");
		
		return extensionSplit[0];
	}
}
