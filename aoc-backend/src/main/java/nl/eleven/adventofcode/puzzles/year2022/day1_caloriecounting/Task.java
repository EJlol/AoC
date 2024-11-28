package nl.eleven.adventofcode.puzzles.year2022.day1_caloriecounting;

import nl.eleven.adventofcode.helpers.list.PartitionListBy;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Component("year2022day1")
public class Task implements TaskInterface<Integer> {

	private static final int LIMIT_SIZE = 3;

	private static Integer sumInnerList(List<String> innerList) {
		return innerList.stream().map(Integer::valueOf).reduce(0, Integer::sum);
	}

	/**
	 * Executes the first task for day 1 of Advent of Code 2022.
	 * This method operates on a list of strings and partitions this list around occurrences of empty lines.
	 * Each partition is then passed to a method that computes a sum of its elements. The method computes
	 * these sums for each partition and returns the maximum one. If no maximum sum is found (which can
	 * happen if there is no input), the method defaults to return 0.
	 * In other words, the method returns the maximum sum computed from all partitions of the input list.
	 *
	 * @param input The list of strings to be partitioned and computed.
	 * @return The maximum sum from all partitions, or 0 if no input is found.
	 */
	@Override
	public Integer executeTask1(List<String> input) {
		return PartitionListBy.emptyLines(input).stream()
				.flatMap(innerList -> Stream.of(sumInnerList(innerList)))
				.max(Integer::compareTo)
				.orElse(0);
	}

	/**
	 * Splits input by empty lines, computes a sum for each group, then returns the total of the largest sums defined by LIMIT_SIZE.
	 * Utilizes ListHelper's partitionByEmptyLines and Task's sumInnerList methods.
	 * If no valid sums are computed or there are fewer than LIMIT_SIZE sums, it may return less than the potential maximum.
	 *
	 * @param input List of strings which are to be grouped and processed.
	 * @return The sum of the largest sums from all partitioned groups, limited by LIMIT_SIZE.
	 */
	@Override
	public Integer executeTask2(List<String> input) {
		return PartitionListBy.emptyLines(input).stream()
				.map(Task::sumInnerList)
				.sorted(Collections.reverseOrder())
				.limit(LIMIT_SIZE)
				.mapToInt(i -> i)
				.sum();
	}
}
