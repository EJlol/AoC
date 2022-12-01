package nl.eleven.adventofcode.puzzles.year2022.day1;

import nl.eleven.adventofcode.Task;
import nl.eleven.adventofcode.mappers.NumberMapper;
import nl.eleven.adventofcode.mappers.SplitByEmptyLines;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component("year2022day1task1")
public class Task1 implements Task {

	@Override
	public int executeTask(List<String> input) {
		List<List<String>> splitInput = SplitByEmptyLines.split(input);
		return splitInput.stream().mapToInt(Task1::sumInnerList).max().orElse(0);
	}

	private static Integer sumInnerList(List<String> innerList) {
		return NumberMapper.map(innerList).stream().reduce(0, Integer::sum);
	}

}
