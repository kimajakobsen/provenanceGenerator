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

import dk.aau.cs.loader.AbstractLoader;
import dk.aau.cs.loader.SSBLoader;

public class App 
{
	public static void main(String [ ] args) {
	
		// create the command line parser
		CommandLineParser parser = new DefaultParser();
		AbstractLoader loader = null;
	
		// create the Options
		Options options = new Options();
		options.addOption("h", "help", false, "Display this message." );
		options.addOption("l", "load", true, "load specified file");
		options.addOption("o", "output", true, "save the result to a tdb database");
		options.addOption("f", "fresh", false, "clear the db before output");
		options.addOption("p", "provenance", false, "generate provenance");
	
		try {
		    CommandLine line = parser.parse( options, args );
		    
		    if (line.hasOption( "help" )) {
		    	printHelp(null,options);
		    	System.exit(0);
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
		    
		    if (line.hasOption( "load" )) {
		    	loader = new SSBLoader(Arrays.asList(line.getOptionValue("load").split(",")));
		    	loader.run(line.hasOption( "provenance"));
		    }
		    
		    if (!line.hasOption( "output" )) {
		    	System.out.println("No output database, use -output <file(s)>");
				System.exit(0);
		    } else {
		    	Config.setDatabasePath(line.getOptionValue("output"));
		    	Config.setBatchSize(50000);
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
