package nl.eleven.adventofcode.puzzles.year2022.day1;

import nl.eleven.adventofcode.Task;
import nl.eleven.adventofcode.helpers.inputmappers.NumberMapper;
import nl.eleven.adventofcode.helpers.list.ListHelper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("year2022day1task1")
public class Task1 implements Task {

	private static Integer sumInnerList(List<String> innerList) {
		return NumberMapper.map(innerList).stream().reduce(0, Integer::sum);
	}

	@Override
	public int executeTask(List<String> input) {
		return ListHelper.partitionByEmptyLines(input).stream()
				.mapToInt(Task1::sumInnerList)
				.max()
				.orElse(0);
	}

}
