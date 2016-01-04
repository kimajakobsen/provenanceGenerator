package dk.aau.cs.loader;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.ext.com.google.common.collect.HashMultimap;
import org.apache.jena.ext.com.google.common.collect.Multimap;
import org.apache.jena.rdf.model.Model;

public abstract class AbstractLoader {
	
	protected List<String> files = new ArrayList<String>();
	// Emptystring is default graph
	protected Multimap<String, Model> models = HashMultimap.create();

//	public Model getModel() {
//		return model;
//	}

	public List<String> getFilePaths() {
		return files;
	}

	public AbstractLoader(String file) {
		files.add(file);
	}
	
	public AbstractLoader(List<String> files) {
		this.files = files;
	}
	
	public abstract void run(boolean b);
	
	
	public abstract void loadToTDB(String location);
	
	
}
