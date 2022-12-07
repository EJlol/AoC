package nl.eleven.adventofcode.tasks;

import java.util.List;

public interface StringTask extends Task {

	String executeTask(List<String> input);

	default String executeTaskAndReturnString(List<String> input) {
		return executeTask(input);
	}
}
