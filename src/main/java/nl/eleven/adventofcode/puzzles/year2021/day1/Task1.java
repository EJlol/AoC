package nl.eleven.adventofcode.puzzles.year2021.day1;

import nl.eleven.adventofcode.Task;
import nl.eleven.adventofcode.readers.NumberReader;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component("year2021day1task1")
public class Task1 implements Task {

	private static final String URL = "https://adventofcode.com/2021/day/1/input";

	public int executeTask() {
		AtomicInteger oldValue = new AtomicInteger(Integer.MAX_VALUE);
		return new NumberReader().read(URL)
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
