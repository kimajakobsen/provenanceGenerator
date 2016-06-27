package dk.aau.cs.helper;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.Duration;
import java.util.Calendar;
import java.util.HashSet;

import org.apache.jena.rdf.model.Model;

import dk.aau.cs.SSB.schema.Schema;
import dk.aau.cs.experimentProfile.ExperimentProfile;
import dk.aau.cs.main.Config;

public class DatasetMetadata {
	String path;
	int facts;
	int informationTriples;
	int provenanceTriples;
	int contextValues;
	long instanceMetadataTriples;
	long structureMetadataTriples;
	private Duration generationDuration;
	
	String lineorderGranularity;
	int lineorderInformationTriples;
	long lineorderProvenanceTriples;
	HashSet<String> lineorderContextValues = new HashSet<String>();
	
	String customerGranularity;
	int customerInformationTriples;
	long customerProvenanceTriples;
	HashSet<String> customerContextValues = new HashSet<String>();
	
	String partGranularity;
	int partInformationTriples;
	long partProvenanceTriples;
	HashSet<String> partContextValues = new HashSet<String>();
	
	String supplierGranularity;
	int supplierInformationTriples;
	long supplierProvenanceTriples;
	HashSet<String> supplierContextValues = new HashSet<String>();
	
	String dateGranularity;
	int dateInformationTriples;
	long dateProvenanceTriples;
	HashSet<String> dateContextValues = new HashSet<String>();
	

	public void writeToDatabase() {
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
		    c = DriverManager.getConnection("jdbc:postgresql://localhost/results",Config.getPsqlUsername(), Config.getPsqlPassword());
		    c.setAutoCommit(false);
		    System.out.println("Opened database successfully");
		    
		    Statement stmt = c.createStatement();
	        String sql = "DELETE from Dataset where path='"+path+"';";
	        stmt.executeUpdate(sql);
	        c.commit();
		    
	        sql = getQuery();
	        	
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
		    stmt.close();
		    c.commit();
	        c.close();
		} catch (Exception e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		    System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	private String getQuery() {
		String sql;
		sql = "INSERT INTO Dataset VALUES ('"+
			path+"',"+
			facts+","+
			getTotalNumberOfInformationTriples()+","+
			getTotalNumberOfProvenanceTriples()+","+
			getTotalNumberOfContextValues()+","+
			generationDuration.toMillis()+",'"+
			lineorderGranularity+"',"+
			lineorderContextValues.size()+","+
			lineorderInformationTriples+","+
			lineorderProvenanceTriples+",'"+
			customerGranularity+"',"+
			customerContextValues.size()+","+
			customerInformationTriples+","+
			customerProvenanceTriples+",'"+
			partGranularity+"',"+
			partContextValues.size()+","+
			partInformationTriples+","+
			partProvenanceTriples+",'"+
			supplierGranularity+"',"+
			supplierContextValues.size()+","+
			supplierInformationTriples+","+
			supplierProvenanceTriples+",'"+
			dateGranularity+"',"+
			dateContextValues.size()+","+
			dateInformationTriples+","+
			dateProvenanceTriples+")";
		return sql;
	}

	public void setNumberOfProvenanceTriples(Schema schema, Model provenanceModel) {
		if (schema.getIdentifierName().equals("lineorder")) {
			lineorderProvenanceTriples = provenanceModel.size();
		} else if (schema.getIdentifierName().equals("customer")) {
			customerProvenanceTriples = provenanceModel.size();
		} else if (schema.getIdentifierName().equals("date")) {
			dateProvenanceTriples = provenanceModel.size();
		} else if (schema.getIdentifierName().equals("part")) {
			partProvenanceTriples = provenanceModel.size();
		} else if (schema.getIdentifierName().equals("supplier")) {
			supplierProvenanceTriples = provenanceModel.size();
		}
	}

	public void setNumberOfStructureMetadataTriples(Model cubeStructure) {
		structureMetadataTriples = cubeStructure.size();		
	}

	public void setGranularity(Schema schema, ExperimentProfile profile) {
		String granularity = profile.getSchemaGranularity(schema.getIdentifierName()).getType().toString();
		if (schema.getIdentifierName().equals("lineorder")) {
			lineorderGranularity = granularity;
		} else if (schema.getIdentifierName().equals("customer")) {
			customerGranularity = granularity;
		} else if (schema.getIdentifierName().equals("date")) {
			dateGranularity = granularity;
		} else if (schema.getIdentifierName().equals("part")) {
			partGranularity = granularity;
		} else if (schema.getIdentifierName().equals("supplier")) {
			supplierGranularity = granularity;
		}
	}

	public void addNumberOfInformationTriples(Schema schema, long size) {
		if (schema.getIdentifierName().equals("lineorder")) {
			lineorderInformationTriples += size;
		} else if (schema.getIdentifierName().equals("customer")) {
			customerInformationTriples += size;
		} else if (schema.getIdentifierName().equals("date")) {
			dateInformationTriples += size;
		} else if (schema.getIdentifierName().equals("part")) {
			partInformationTriples += size;
		} else if (schema.getIdentifierName().equals("supplier")) {
			supplierInformationTriples += size;
		}
	}

	public void setPath(String databasePath) {
		path = databasePath;
	}

	public void addProveanceIdentifier(Schema schema,String provenanceIdentifier) {
		if (schema.getIdentifierName().equals("lineorder")) {
			lineorderContextValues.add(provenanceIdentifier);
		} else if (schema.getIdentifierName().equals("customer")) {
			customerContextValues.add(provenanceIdentifier);
		} else if (schema.getIdentifierName().equals("date")) {
			dateContextValues.add(provenanceIdentifier);
		} else if (schema.getIdentifierName().equals("part")) {
			partContextValues.add(provenanceIdentifier);
		} else if (schema.getIdentifierName().equals("supplier")) {
			supplierContextValues.add(provenanceIdentifier);
		}
	}

	public void incrementNumberOfFacts(Schema schema) {
		if (schema.getIdentifierName().equals("lineorder")) {
			facts++;
		}
	}
	
	public int getTotalNumberOfInformationTriples() {
		return lineorderInformationTriples+customerInformationTriples+partInformationTriples+supplierInformationTriples+dateInformationTriples;
	}
	
	public long getTotalNumberOfProvenanceTriples() {
        return lineorderProvenanceTriples+customerProvenanceTriples+partProvenanceTriples+supplierProvenanceTriples+dateProvenanceTriples;
	}
	
	public int getTotalNumberOfContextValues() {
		return lineorderContextValues.size()+customerContextValues.size()+supplierContextValues.size()+partContextValues.size()+dateContextValues.size();
	}

	public void addNumberOfInstanceMetadataTriples(long size) {
		instanceMetadataTriples += size;
	}

	public void setGenerationDuration(Duration between) {
		this.generationDuration = between;
	}

	public void writeToFile() {
		PrintWriter writer;
		try {
			System.out.println(String.valueOf(Calendar.getInstance().getTime().getTime()));
			writer = new PrintWriter(String.valueOf(Calendar.getInstance().getTime().getTime()), "UTF-8");
			String query = getQuery();
			writer.println(query);
		    writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
