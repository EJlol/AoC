package nl.eleven.adventofcode.tasks;

import java.util.List;

public interface StringDoubleTask extends Task {

	String executeTask1(List<String> input);

	String executeTask2(List<String> input);

	default String executeTaskAndReturnString(int taskNumber, List<String> input) {
		if (taskNumber == 1) {
			return executeTask1(input);
		} else {
			return executeTask2(input);
		}
	}
}
