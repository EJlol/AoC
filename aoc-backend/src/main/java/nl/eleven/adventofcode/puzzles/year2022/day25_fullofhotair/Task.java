package nl.eleven.adventofcode.puzzles.year2022.day25_fullofhotair;

import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("year2022day25")
public class Task implements TaskInterface<String> {

	@Override
	public String executeTask1(List<String> input) {
		return "" + input.stream()
				.map(s -> s
						.replace("2", "4")
						.replace("1", "3")
						.replace("0", "2")
						.replace("-", "1")
						.replace("=", "0")
				)
				.mapToInt(s -> Integer.parseInt(s, 5))
				.sum();
	}

	@Override
	public String executeTask2(List<String> input) {
		return "";
	}
}
