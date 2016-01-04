package dk.aau.cs.main;

import org.apache.jena.shared.ConfigException;

public class Config {

	private static String namespace = "www.example.com/";
	private static String provenanceNamespace = namespace+"provenance/";
	private static int counter = 0;
	private static boolean fresh = false;

	public static String getNamespace() {
		return namespace;
	}
	
	public static String getProvenanceNamespace() {
		return provenanceNamespace;
	}
	
	public static String getUniqueProvenanceGraph() throws ConfigException {
		if (!Config.isFresh()) {
			throw new ConfigException("Cannot guarantee unique provenance graph id because -fresh flag was not used. Implement new counter to resolve this problem.");
			//A good solution would be to use blank nodes.
		}
		String graphName = namespace+"ID/"+counter;
		counter++;
		return graphName;
	}

	public static boolean isFresh() {
		return fresh;
	}

	public static void setFresh(boolean fresh) {
		Config.fresh = fresh;
	}


	
	
}
