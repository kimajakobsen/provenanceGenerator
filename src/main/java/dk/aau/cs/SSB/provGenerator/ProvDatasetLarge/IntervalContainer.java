package dk.aau.cs.SSB.provGenerator.ProvDataset;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.apache.commons.lang3.tuple.Pair;

public class IntervalContainer {
	private static HashMap<Integer,Pair<LocalDateTime,LocalDateTime>> intervals = 
				new HashMap<Integer,Pair<LocalDateTime,LocalDateTime>>();

	public static Pair<LocalDateTime,LocalDateTime> getIntervalLevel(int level) {
		if (!intervals.containsKey(level)) {
			if (level == 1) { 
				intervals.put(1, Pair.of(LocalDateTime.now(),LocalDateTime.now().plusHours(1)));
			} else {
				 Pair<LocalDateTime, LocalDateTime> lastTimeIntevalPair = getIntervalLevel(level-1);
				 LocalDateTime startTime = lastTimeIntevalPair.getRight();
				 intervals.put(level, Pair.of(startTime, startTime.plusHours(1)));
			}
		}
		return intervals.get(level);
	}
}
