package nl.eleven.adventofcode.puzzles.year2021.day1;

import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

@Component("year2021day1")
public class Task implements TaskInterface<Integer> {

	private static final int WINDOW_SIZE = 3;

	/**
	 * This method is used to execute the first task. It takes a list of strings as input and returns an Integer.
	 * The method uses a for loop to process the input. It maintains a running total (sum) and a previous value.
	 * For each value in the input, it checks if the value is larger than the previous value.
	 * If it is, it increments the sum by 1. The previous value is then updated to the current value.
	 * The method finally returns the sum, which represents the total number of times a value was larger than the previous value.
	 *
	 * @param input A list of strings representing the input data.
	 * @return An Integer representing the total number of times a value was larger than the previous value.
	 */
	@Override
	public Integer executeTask1(List<String> input) {
		int sum = 0;
		int previousValue = Integer.parseInt(input.getFirst());

		for (int i = 1; i < input.size(); i++) {
			int currentValue = Integer.parseInt(input.get(i));
			if (currentValue > previousValue) {
				sum++;
			}
			previousValue = currentValue;
		}

		return sum;
	}

	/**
	 * This method is used to execute the second task. It takes a list of strings as input and returns an Integer.
	 * The method uses a for loop to process the input. It maintains a running total (sum) and a deque of previous values.
	 * For each value in the input, it checks if the total of new values is larger than the total of last values.
	 * If it is, it increments the sum by 1. The deque is then updated to hold the last WINDOW_SIZE values.
	 * The method finally returns the sum, which represents the total number of times the total of new values was larger than the total of last values.
	 *
	 * @param input A list of strings representing the input data.
	 * @return An Integer representing the total number of times the total of new values was larger than the total of last values.
	 */
	public Integer executeTask2(List<String> input) {
		// A deque to hold the last WINDOW_SIZE values
		Deque<Integer> previousValues = new ArrayDeque<>();
		// A variable to hold the running total
		int sum = 0;

		// Loop through the input list
		for (int i = 0; i < input.size(); i++) {
			// Calculate the total of last values
			int totalLastValues = previousValues.stream().reduce(0, Integer::sum);

			// Parse the current value from the input list
			int value = Integer.parseInt(input.get(i));
			// Add the current value to the deque
			previousValues.addLast(value);

			// If the deque has more than WINDOW_SIZE values, remove the first value
			if (previousValues.size() > WINDOW_SIZE) {
				previousValues.removeFirst();
			}

			// If the deque has WINDOW_SIZE values and it's not the first WINDOW_SIZE values,
			// calculate the total of new values and compare it with the total of last values
			if (previousValues.size() == WINDOW_SIZE && i != (WINDOW_SIZE - 1)) {
				int totalNewValues = previousValues.stream().reduce(0, Integer::sum);

				// If the total of new values is larger than the total of last values, increment the sum
				if (totalNewValues > totalLastValues) {
					sum++;
				}
			}
		}

		// Return the sum
		return sum;
	}
}
