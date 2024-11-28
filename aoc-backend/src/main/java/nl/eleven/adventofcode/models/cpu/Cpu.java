package nl.eleven.adventofcode.models.cpu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Cpu<S> implements RegisterInterface {

	private static final Logger logger = LoggerFactory.getLogger(Cpu.class);

	private final List<S> program;

	private final Map<Integer, Integer> registers;

	private int busyCycleCount;

	private Instruction<S> busyInstruction;

	private List<S> busyInstructionParameters;

	private int cycleCount;

	private int programCounter;

	protected Cpu(List<S> program) {
		this.program = program;
		programCounter = 0;

		cycleCount = 0;
		busyInstruction = null;
		busyInstructionParameters = new ArrayList<>();
		busyCycleCount = 0;

		registers = initializeRegisters();
	}

	public void addRegister(int registerIndex, int value) {
		registers.put(registerIndex, registers.getOrDefault(registerIndex, 0) + value);
	}

	public void debug() {
		logger.debug("%d: PC[%d] - %s".formatted(cycleCount, programCounter, busyInstruction != null ? busyInstruction.getInstructionName() : "null"));
	}

	public void doCycle() {
		if (busyCycleCount <= 0) {
			// Execute instruction
			if (busyInstruction != null) {
				busyInstruction.execute(this, busyInstructionParameters);
			}

			S line = program.get(programCounter);
			List<S> parameters = getParametersForLine(line);
			programCounter++;

			busyInstruction = getInstruction(parameters.getFirst());
			parameters.removeFirst();
			busyInstructionParameters = parameters;
			busyCycleCount = busyInstruction.getCycles();
		}
		busyCycleCount--;
		cycleCount++;
	}

	public int getCycleCount() {
		return cycleCount;
	}

	public abstract Instruction<S> getInstruction(S instruction);

	public abstract List<S> getParametersForLine(S line);

	public List<S> getProgram() {
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
