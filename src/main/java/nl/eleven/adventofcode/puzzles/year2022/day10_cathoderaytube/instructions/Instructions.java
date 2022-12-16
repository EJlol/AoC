package nl.eleven.adventofcode.puzzles.year2022.day10_cathoderaytube.instructions;

import nl.eleven.adventofcode.models.cpu.Cpu;
import nl.eleven.adventofcode.models.cpu.Instruction;

import java.util.List;

public enum Instructions implements Instruction {

	NOOP(1, "noop"),
	ADDX(2, "addx");

	private final int cycles;

	private final String name;

	Instructions(int cycles, String name) {
		this.cycles = cycles;
		this.name = name;
	}

	@Override
	public void execute(Cpu cpu, List<String> parameters) {
		if (this == Instructions.ADDX) {
			int addValue = Integer.parseInt(parameters.get(0));
			cpu.addToRegister(0, addValue);
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
