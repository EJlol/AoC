package nl.eleven.adventofcode.models.cpu;

import nl.eleven.adventofcode.helpers.string.StringHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Cpu {

	private final Map<Integer, Integer> registers;

	private int busyCycleCount;

	private Instruction busyInstruction;

	private List<String> busyInstructionParameters;

	private int cycleCount;

	private final List<String> program;

	private int programCounter;

	protected Cpu(List<String> program) {
		this.program = program;
		programCounter = 0;

		cycleCount = 0;
		busyInstruction = null;
		busyInstructionParameters = new ArrayList<>();
		busyCycleCount = 0;

		registers = initializeRegisters();
	}

	public void addToRegister(int registerIndex, int value) {
		registers.compute(registerIndex, (k, oldValue) -> oldValue == null ? value : oldValue + value);
	}

	public void debug() {
		System.out.println(cycleCount + ": PC[" + programCounter + "] - " + (busyInstruction != null ? busyInstruction.getInstructionName() : "null"));
	}

	public void doCycle() {
		if (busyCycleCount <= 0) {
			// Execute instruction
			if (busyInstruction != null) {
				busyInstruction.execute(this, busyInstructionParameters);
			}

			String line = program.get(programCounter);
			programCounter++;

			List<String> parameters = StringHelper.splitAtCharacter(line, ' ');
			busyInstruction = getInstruction(parameters.get(0));
			parameters.remove(0);
			busyInstructionParameters = parameters;
			busyCycleCount = busyInstruction.getCycles();
		}
		busyCycleCount--;
		cycleCount++;
	}

	public int getCycleCount() {
		return cycleCount;
	}

	public abstract Instruction getInstruction(String instruction);

	public List<String> getProgram() {
		return program;
	}

	public int getProgramCounter() {
		return programCounter;
	}

	public int getRegisterValue(int registerIndex) {
		return registers.getOrDefault(registerIndex, 0);
	}

	public abstract Map<Integer, Integer> initializeRegisters();

	public abstract boolean shouldStop();
}
