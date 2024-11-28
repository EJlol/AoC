package nl.eleven.adventofcode.tasks;

import java.util.List;

/**
 * Represents an interface for a task that can be executed.
 *
 * @param <T> the type of the result returned by the task
 */
public interface TaskInterface<T> {

	T executeTask1(List<String> input);

	T executeTask2(List<String> input);

	default String executeTaskAndReturnString(int taskNumber, List<String> input) {
		if (taskNumber == 1) {
			return executeTask1(input).toString();
		} else {
			return executeTask2(input).toString();
		}
	}
}
