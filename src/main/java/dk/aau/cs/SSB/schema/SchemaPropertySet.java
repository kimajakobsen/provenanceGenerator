package dk.aau.cs.SSB.schema;

public class SchemaPropertySet {

	private String name;
	private String objectPropertyName;
	private Boolean isPartOfKey;
	private String type;
	
	public SchemaPropertySet(String name, String objectPropertyName, boolean isPartOfKey, String type) {
		this.name = name;
		this.objectPropertyName = objectPropertyName;
		this.isPartOfKey = isPartOfKey;
		this.setType(type);
	}
	public SchemaPropertySet(String name, String objectPropertyName, boolean isPartOfKey) {
		this(name,objectPropertyName,isPartOfKey,"");
	}
	public SchemaPropertySet(String name, String objectPropertyName) {
		this(name,objectPropertyName,false);
	}
	public SchemaPropertySet(String name) {
		this(name,"",false);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getObjectPropertyName() {
		return objectPropertyName;
	}
	public void setObjectPropertyName(String objectPropertyName) {
		this.objectPropertyName = objectPropertyName;
	}
	public Boolean isPartOfKey() {
		return isPartOfKey;
	}
	public void setIsInverseFunctionalProperty(
			Boolean isInverseFunctionalProperty) {
		this.isPartOfKey = isInverseFunctionalProperty;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
