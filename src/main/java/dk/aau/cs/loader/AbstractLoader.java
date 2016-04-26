package dk.aau.cs.loader;

import java.io.File;
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

	public AbstractLoader(List<String> files) {
		this.files = addFilesInFolder(files);
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
