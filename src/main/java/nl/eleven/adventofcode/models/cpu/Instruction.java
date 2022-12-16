package nl.eleven.adventofcode.models.cpu;

import java.util.List;

public interface Instruction {

	void execute(Cpu cpu, List<String> parameters);

	int getCycles();

	String getInstructionName();
}
