package nl.eleven.adventofcode.tasks;

import java.util.List;

public interface IntegerSingleTask extends Task {

	int executeTask(List<String> input);

	default String executeTaskAndReturnString(int taskNumber, List<String> input) {
		return Integer.toString(executeTask(input));
	}
}
