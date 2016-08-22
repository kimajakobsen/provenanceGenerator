package dk.aau.cs.loader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.jena.rdf.model.Model;

import dk.aau.cs.experimentProfile.ExperimentProfile;

public abstract class AbstractLoader {
	
	protected List<String> files = new ArrayList<String>();
	private HashMap<String, Model> informationTripleModels = new HashMap<String,Model>();
	private HashMap<String, Model> largeModels = new HashMap<String,Model>();
	private ArrayList<String> largeGraphNames = new ArrayList<String>();

	public List<String> getFilePaths() {
		return files;
	}

	public AbstractLoader(List<String> files) {
		this.files = addFilesInFolder(files);
		largeGraphNames.add("http://example.com/provenance/");
		largeGraphNames.add("http://example.com/CubeInstanceMetadata");
		
	}
	
	private List<String> addFilesInFolder(List<String> files) {
		List<String> result = new ArrayList<String>();
		for (String string : files) {
			File file = new File(string);
			if (file.isDirectory()) {
				for (final File fileEntry : file.listFiles()) {
					result.add(fileEntry.toString());
			    }
			} else {
				result.add(string);
			}
		}
		return result;
	}
	
	public HashMap<String,Model> getModelContainer() {
		return informationTripleModels;
	}
	
	public HashMap<String,Model> getLargeModelContainer() {
		return largeModels;
	}
	
	public void insertIntoModelContainer(String graph, Model model) {
		if (largeGraphNames.contains(graph)) {
			addToModel(graph, model,largeModels);
		} else {
			addToModel(graph, model,informationTripleModels);
		}
		
	}

	private void addToModel(String graph, Model model, HashMap<String, Model> store) {
		if (store.containsKey(graph)) {
			Model temp = store.get(graph).add(model);
			store.put(graph, temp);
		} else {
			store.put(graph, model);
		}
	}
	
	public void resetModelContainer() {
		informationTripleModels.clear();
	}
	
	public long getModelContainerSize() {
		long size = 0;
		for (Entry<String, Model> model : informationTripleModels.entrySet()) {
			//System.out.println(model.getKey() +" has " + model.getValue().size() + " triples");
			size += model.getValue().size();
		}
		return size;
	}
	
	public abstract void run(ExperimentProfile profile);
	
	public abstract void writeToTDB(String location, HashMap<String,Model> modelContainer);
}
