package dk.aau.cs.loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.jena.rdf.model.Model;

public abstract class AbstractLoader {
	
	protected List<String> files = new ArrayList<String>();
	private HashMap<String, Model> models = new HashMap<String,Model>();

	public List<String> getFilePaths() {
		return files;
	}

	public AbstractLoader(String file) {
		files.add(file);
	}
	
	public AbstractLoader(List<String> files) {
		this.files = files;
	}
	
	public HashMap<String,Model> getModelContainer() {
		return models;
	}
	
	public void insertIntoModelContainer(String graph, Model model) {
		if (models.containsKey(graph)) {
			Model temp = models.get(graph).add(model);
			models.put(graph, temp);
		} else {
			models.put(graph, model);
		}
	}
	
	public void resetModelContainer() {
		models.clear();
	}
	
	public long getModelContainerSize() {
		long size = 0;
		for (Entry<String, Model> model : models.entrySet()) {
			//System.out.println(model.getKey() +" has " + model.getValue().size() + " triples");
			size += model.getValue().size();
		}
		return size;
	}
	
	public abstract void run(boolean b);
	
	public abstract void loadToTDB(String location);
}
