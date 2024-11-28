package nl.eleven.adventofcode.helpers.list;

import com.google.common.collect.Lists;
import nl.eleven.adventofcode.models.pair.SimplePair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * This class provides utility methods for partitioning a list based on various conditions.
 * It is not meant to be instantiated.
 */
public class PartitionListBy {

	/**
	 * Private constructor to prevent instantiation of this utility class.
	 */
	private PartitionListBy() {

	}

	/**
	 * Partitions the input list into sublists of a given size.
	 * Example: If the input list is ["a", "b", "c", "d", "e", "f"] and the count is 3,
	 * the output will be [["a", "b", "c"], ["d", "e", "f"]].
	 *
	 * @param input The list to be partitioned.
	 * @param count The size of each partition.
	 * @return A list of partitions, where each partition is a list of strings.
	 */
	public static List<List<String>> count(List<String> input, int count) {
		return Lists.partition(input, count);
	}

	/**
	 * Partitions the input list at each empty line.
	 * Example: If the input list is ["a", "", "b", "c", "", "d", "e", "f"],
	 * the output will be [["a"], ["b", "c"], ["d", "e", "f"]].
	 *
	 * @param input The list to be partitioned.
	 * @return A list of partitions, where each partition is a list of strings.
	 */
	public static List<List<String>> emptyLines(List<String> input) {
		return predicate(input, String::isEmpty, false);
	}

	/**
	 * Partitions the input list at each empty line and pairs the first two elements of each partition.
	 * Example: If the input list is ["a", "b", "", "c", "d", "", "e", "f"],
	 * the output will be [(a, b), (c, d), (e, f)].
	 *
	 * @param input The list to be partitioned.
	 * @return A list of pairs, where each pair consists of the first two elements of a partition.
	 */
	public static List<SimplePair<String>> inTwoByEmptyLine(List<String> input) {
		List<List<String>> partitions = emptyLines(input);
		return partitions.stream()
				.map(innerList -> new SimplePair<>(innerList.getFirst(), innerList.get(1)))
				.toList();
	}

	/**
	 * Partitions the input list based on a given predicate.
	 *
	 * @param input             The list to be partitioned.
	 * @param predicate         The condition for partitioning the list.
	 * @param includeFirstEntry Whether to include the first entry of each partition in the partition itself.
	 * @return A list of partitions, where each partition is a list of strings.
	 */
	public static List<List<String>> predicate(List<String> input, Predicate<String> predicate,
											   boolean includeFirstEntry) {
		List<List<String>> result = new ArrayList<>();
		if (input.isEmpty()) {
			return result;
		}

		List<String> currentPartitionList = new ArrayList<>();
		result.add(currentPartitionList);

		for (String line : input) {
			if (predicate.test(line)) {
				currentPartitionList = new ArrayList<>();
				if (includeFirstEntry) {
					currentPartitionList.add(line);
				}
				result.add(currentPartitionList);
			} else {
				currentPartitionList.add(line);
			}
		}
		return result;
	}

	/**
	 * Partitions the input list at each line that starts with a given prefix.
	 * Example: If the input list is ["apple", "banana", "cherry", "date", "elderberry", "fig", "grape"] and the prefix is "e",
	 * the output will be [["apple", "banana", "cherry", "date"], ["elderberry", "fig", "grape"]].
	 *
	 * @param input  The list to be partitioned.
	 * @param prefix The prefix to partition the list at.
	 * @return A list of partitions, where each partition is a list of strings.
	 */
	public static List<List<String>> startsWith(List<String> input, String prefix) {
		return predicate(input, line -> line.startsWith(prefix), true);
	}
}
