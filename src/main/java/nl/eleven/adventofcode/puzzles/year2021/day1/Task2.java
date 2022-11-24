package nl.eleven.adventofcode.puzzles.year2021.day1;

import nl.eleven.adventofcode.Task;
import nl.eleven.adventofcode.readers.NumberReader;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;

@Component("year2021day1task2")
public class Task2 implements Task {

	private static final String URL = "https://adventofcode.com/2021/day/1/input";

	private static final int WINDOW_SIZE = 3;

	public int executeTask() {
		Deque<Integer> oldValues = new ArrayDeque<>();

		return NumberReader.read(URL)
				.reduce(0, (sum, value) -> {
					int totalLastValues = oldValues.size() == WINDOW_SIZE ? oldValues.stream()
							.reduce(0, Integer::sum) : Integer.MAX_VALUE;

					oldValues.addLast(value);
					if (oldValues.size() > WINDOW_SIZE) {
						oldValues.removeFirst();
					}

					int totalNewValues = oldValues.stream().reduce(0, Integer::sum);
					return oldValues.size() == WINDOW_SIZE && totalNewValues > totalLastValues ? sum + 1 : sum;
				});
	}
}
