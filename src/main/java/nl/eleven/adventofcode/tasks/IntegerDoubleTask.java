package nl.eleven.adventofcode.tasks;

import java.util.List;

public interface IntegerDoubleTask extends Task {

	int executeTask1(List<String> input);

	int executeTask2(List<String> input);

	default String executeTaskAndReturnString(int taskNumber, List<String> input) {
		if (taskNumber == 1) {
			return Integer.toString(executeTask1(input));
		} else {
			return Integer.toString(executeTask2(input));
		}
	}
}
