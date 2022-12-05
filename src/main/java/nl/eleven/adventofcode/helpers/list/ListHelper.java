package nl.eleven.adventofcode.helpers.list;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class ListHelper {

	private ListHelper() {

	}

	public static List<List<String>> partitionByCount(List<String> input, int count) {
		return Lists.partition(input, count);
	}

	public static List<List<String>> partitionByEmptyLines(List<String> input) {
		List<List<String>> result = new ArrayList<>();

		List<String> currentList = new ArrayList<>();
		result.add(currentList);

		for (String line : input) {
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
