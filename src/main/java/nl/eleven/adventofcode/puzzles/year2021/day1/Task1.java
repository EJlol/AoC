package nl.eleven.adventofcode.puzzles.year2021.day1;

import nl.eleven.adventofcode.Task;
import nl.eleven.adventofcode.readers.NumberReader;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Component("year2021day1task1")
public class Task1 implements Task {

	@Override
	public int executeTask(Stream<String> stream) {
		AtomicInteger oldValue = new AtomicInteger(Integer.MAX_VALUE);
		return new NumberReader().parse(stream)
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
