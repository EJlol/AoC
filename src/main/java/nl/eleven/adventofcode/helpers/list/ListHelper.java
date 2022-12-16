package nl.eleven.adventofcode.helpers.list;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class ListHelper {

	private ListHelper() {

	}

	public static List<List<String>> partitionBy(List<String> input, Predicate<String> predicate, boolean includeAll) {
		List<List<String>> result = new ArrayList<>();

		List<String> currentList = new ArrayList<>();
		result.add(currentList);

		for (String line : input) {
			if (predicate.test(line)) {
				currentList = new ArrayList<>();
				result.add(currentList);
				if (includeAll) {
					currentList.add(line);
				}
			} else {
				currentList.add(line);
			}
		}
		return result;
	}

	public static List<List<String>> partitionByCount(List<String> input, int count) {
		return Lists.partition(input, count);
	}

	public static List<List<String>> partitionByEmptyLines(List<String> input) {
		return partitionBy(input, String::isEmpty, false);
	}

	public static List<List<String>> partitionByStartsWith(List<String> input, String prefix) {
		return partitionBy(input, line -> line.startsWith(prefix), true);
	}

	public static <T> List<T> reverse(List<T> list) {
		List<T> resultList = new ArrayList<>(list);
		Collections.reverse(resultList);
		return resultList;
	}
}
