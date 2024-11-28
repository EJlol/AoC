package nl.eleven.adventofcode.puzzles.year2022.day10_cathoderaytube.instructions;

import nl.eleven.adventofcode.models.cpu.Instruction;
import nl.eleven.adventofcode.models.cpu.RegisterInterface;

import java.util.List;

public enum Instructions implements Instruction<String> {

	NOOP(1, "noop"),
	ADDX(2, "addx");

	private final int cycles;

	private final String name;

	Instructions(int cycles, String name) {
		this.cycles = cycles;
		this.name = name;
	}

	@Override
	public void execute(RegisterInterface registerInterface, List<String> parameters) {
		if (this == Instructions.ADDX) {
			int addValue = Integer.parseInt(parameters.getFirst());
			registerInterface.addRegister(0, addValue);
		}
	}

	@Override
	public int getCycles() {
		return this.cycles;
	}

	@Override
	public String getInstructionName() {
		return this.name;
	}
}
