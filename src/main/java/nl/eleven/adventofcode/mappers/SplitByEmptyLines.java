package nl.eleven.adventofcode.mappers;

import java.util.ArrayList;
import java.util.List;

public class SplitByEmptyLines {
	public static List<List<String>> split(List<String> input) {
		List<List<String>> result = new ArrayList<>();

		List<String> currentList = new ArrayList<>();
		result.add(currentList);

		for(String line : input) {
			if (line.isEmpty()) {
				currentList = new ArrayList<>();
				result.add(currentList);
			} else {
				currentList.add(line);
			}
		}

		return result;
	}
}
