package dk.aau.cs.main;

import java.io.File;
import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;

import dk.aau.cs.experimentProfile.ExperimentMinimalProvenanceGraph;
import dk.aau.cs.experimentProfile.ExperimentProfile;
import dk.aau.cs.experimentProfile.ExperimentSplitProvenanceGraph;
import dk.aau.cs.loader.AbstractLoader;
import dk.aau.cs.loader.SSBLoader;

public class App 
{
	public static void main(String [ ] args) {
	
		// create the command line parser
		CommandLineParser parser = new DefaultParser();
		AbstractLoader loader = null;
		ExperimentProfile profile = new ExperimentMinimalProvenanceGraph();
	
		// create the Options
		Options options = new Options();
		options.addOption("h", "help", false, "Display this message." );
		options.addOption("l", "load", true, "load specified file");
		options.addOption("o", "output", true, "save the result to a tdb database");
		options.addOption("f", "fresh", false, "clear the db before output");
		options.addOption("u", "user", true, "localhost psql username");
		options.addOption("w", "password", true, "localhost psql password");
		options.addOption("b", "batch", true, "size of batches that are saved to disk");
		options.addOption("p", "provenanceGraphSize", true, "The size of the provenance template (minumum,large,split)");
	
		try {
		    CommandLine line = parser.parse( options, args );
		    
		    if (line.hasOption( "help" )) {
		    	printHelp(null,options);
		    	System.exit(0);
			} 
		    
		    if (line.hasOption("provenanceGraphSize")) {
		    	//Default is large graph
		    	System.out.println(line.getOptionValue("provenanceGraphSize"));
				if (line.getOptionValue("provenanceGraphSize").equals("minimum")) {
					profile = new ExperimentMinimalProvenanceGraph();
				}
				else if (line.getOptionValue("provenanceGraphSize").equals("split")) {
					profile = new ExperimentSplitProvenanceGraph();
				}
			}
		    
		    if (line.hasOption("batch")) {
				Config.setBatchSize(Integer.valueOf(line.getOptionValue("batch")));
			}
		    
		    if (line.hasOption("user")) {
				Config.setUsername(line.getOptionValue("user"));
			}
		    
		    if (line.hasOption("password")) {
				Config.setPassword(line.getOptionValue("password"));
			}
		    
		    if (line.hasOption("fresh")) {
		    	if (line.hasOption( "output" )) {
		    		FileUtils.cleanDirectory(new File(line.getOptionValue("output"))); 
				} else {
					System.out.println("No output database, use -output <file(s)>");
					System.exit(0);
				}
		    	Config.setFresh(true);
			}
		    
		    if (!line.hasOption( "output" )) {
		    	System.out.println("No output database, use -output <file(s)>");
				System.exit(0);
		    } else {
		    	Config.setDatabasePath(line.getOptionValue("output"));
		    }
		    
		    if (line.hasOption( "load" )) {
		    	loader = new SSBLoader(Arrays.asList(line.getOptionValue("load").split(",")));
		    	loader.run(profile);
		    }
		}
		catch( ParseException exp ) {
			printHelp(exp, options);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
	
	private static void printHelp(ParseException exp, Options options) {
		String header = "";
		HelpFormatter formatter = new HelpFormatter();
		if (exp != null) {
			header = "Unexpected exception:" + exp.getMessage();
		}
		formatter.printHelp("myapp", header, options, null, true);
	}
}
