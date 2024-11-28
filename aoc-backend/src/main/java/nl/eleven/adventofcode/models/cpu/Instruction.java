package nl.eleven.adventofcode.models.cpu;

import java.util.List;

public interface Instruction<S> {

	void execute(RegisterInterface registers, List<S> parameters);

	int getCycles();

	String getInstructionName();

}
