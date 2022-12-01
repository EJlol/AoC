package nl.eleven.adventofcode.puzzles.year2021.day1;

import nl.eleven.adventofcode.Task;
import nl.eleven.adventofcode.mappers.NumberMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component("year2021day1task1")
public class Task1 implements Task {

	@Override
	public int executeTask(List<String> input) {
		AtomicInteger oldValue = new AtomicInteger(Integer.MAX_VALUE);
		return new NumberMapper().map(input)
				.stream()
				.reduce(0, (sum, value) -> {
					if (value > oldValue.get()) {
						oldValue.set(value);
						return sum + 1;
					}
					oldValue.set(value);
					return sum;
				});
	}
}
