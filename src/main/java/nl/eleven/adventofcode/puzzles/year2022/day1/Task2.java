package nl.eleven.adventofcode.puzzles.year2022.day1;

import nl.eleven.adventofcode.Task;
import nl.eleven.adventofcode.mappers.NumberMapper;
import nl.eleven.adventofcode.mappers.SplitByEmptyLines;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component("year2022day1task2")
public class Task2 implements Task {

	@Override
	public int executeTask(List<String> input) {
		List<List<String>> splitInput = SplitByEmptyLines.split(input);
		return splitInput.stream()
				.map(Task2::sumInnerList)
				.sorted(Collections.reverseOrder())
				.limit(3)
				.mapToInt(i -> i)
				.sum();
	}

	private static Integer sumInnerList(List<String> innerList) {
		return NumberMapper.map(innerList).stream().reduce(0, Integer::sum);
	}

}
