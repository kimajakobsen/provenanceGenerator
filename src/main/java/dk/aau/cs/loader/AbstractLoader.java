package dk.aau.cs.loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.jena.ext.com.google.common.collect.HashMultimap;
import org.apache.jena.ext.com.google.common.collect.Multimap;
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
	
	public abstract void run(boolean b);
	
	
	public abstract void loadToTDB(String location);
	
	
}
