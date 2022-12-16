package nl.eleven.adventofcode.tasks;

import java.util.List;

public interface StringSingleTask extends Task {

	String executeTask(List<String> input);

	default String executeTaskAndReturnString(int taskNumber, List<String> input) {
		return executeTask(input);
	}
}
