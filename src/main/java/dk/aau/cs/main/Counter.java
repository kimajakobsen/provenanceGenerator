package dk.aau.cs.main;

import java.util.HashMap;

public class Counter {
	private static HashMap<String, Integer> counters = new HashMap<String, Integer>();

	public static int getCounter(String type) {
		if (counters.containsKey(type)) {
			int counter = counters.get(type);
			counters.put(type, ++counter);
			return counter;
		} else {
			counters.put(type, 2);
			return 1;
		}
		
	}
}
