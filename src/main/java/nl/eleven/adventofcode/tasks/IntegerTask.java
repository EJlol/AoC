package nl.eleven.adventofcode.tasks;

import java.util.List;

public interface IntegerTask extends Task {

	int executeTask(List<String> input);

	default String executeTaskAndReturnString(List<String> input) {
		return Integer.toString(executeTask(input));
	}
}
