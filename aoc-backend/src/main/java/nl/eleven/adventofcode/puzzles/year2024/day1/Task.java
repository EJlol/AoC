package nl.eleven.adventofcode.puzzles.year2024.day1;

import nl.eleven.adventofcode.helpers.list.ListHelper;
import nl.eleven.adventofcode.helpers.string.StringSplitter;
import nl.eleven.adventofcode.models.pair.Pair;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("year2024day1")
public class Task implements TaskInterface<Integer> {

	private static void readLists(List<String> input, List<Integer> left, List<Integer> right) {
		input.forEach(line -> {
			List<String> values = StringSplitter.splitAtWhitespace(line);
			left.add(Integer.parseInt(values.get(0)));
			right.add(Integer.parseInt(values.get(1)));
		});
	}

	@Override
	public Integer executeTask1(List<String> input) {
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();
		readLists(input, left, right);

		// Sort lists
		left.sort(Integer::compareTo);
		right.sort(Integer::compareTo);

		List<Pair<Integer, Integer>> zipped = ListHelper.zip(left, right);
		return zipped.stream()
				.mapToInt(pair -> Math.abs(pair.getLeft() - pair.getRight()))
				.sum();
	}

	@Override
	public Integer executeTask2(List<String> input) {
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();
		readLists(input, left, right);

		Map<Integer, Long> counts = ListHelper.countEqualItems(right);
		int sum = 0;
		for (int leftValue : left) {
			Long amount = counts.get(leftValue);
			if (amount == null) {
				continue;
			}

			sum += leftValue * amount.intValue();
		}
		return sum;
	}
}
