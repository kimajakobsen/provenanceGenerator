package dk.aau.cs.SSB.schema;

import javax.naming.InvalidNameException;

public class SchemaBuilder {

	public Schema build(String file) throws InvalidNameException {
		file = getFileNameWithoutExtension(file).toLowerCase();
		
		if (file.startsWith("customer")) {
			return new Customer();
		} else if (file.startsWith("supplier")) {
			return new Supplier();
		} else if (file.startsWith("lineorder")) {
			return new Lineorder();
		} else if (file.startsWith("part")) {
			return new Part();
		} else if (file.startsWith("date")) {
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
