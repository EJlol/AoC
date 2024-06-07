package nl.eleven.adventofcode.puzzles.year2022.day10_cathoderaytube.instructions;

import nl.eleven.adventofcode.helpers.string.StringSplitter;
import nl.eleven.adventofcode.models.cpu.Cpu;
import nl.eleven.adventofcode.models.cpu.Instruction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CpuImpl extends Cpu<String> {

	public CpuImpl(List<String> instructions) {
		super(instructions);
	}

	@Override
	public List<String> getParametersForLine(String line) {
		return StringSplitter.splitAtCharacter(line, ' ');
	}

	@Override
	public Instruction<String> getInstruction(String instruction) {
		return switch (instruction) {
			case "addx" -> Instructions.ADDX;
			default -> Instructions.NOOP;
		};
	}

	@Override
	public Map<Integer, Integer> initializeRegisters() {
		Map<Integer, Integer> registers = new HashMap<>();
		registers.put(0, 1);
		return registers;
	}

	public boolean shouldStop() {
		return super.getProgramCounter() == super.getProgram().size();
	}
}
