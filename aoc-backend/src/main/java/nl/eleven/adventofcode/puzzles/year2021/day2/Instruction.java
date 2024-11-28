package nl.eleven.adventofcode.puzzles.year2021.day2;

public class Instruction {

	private final Command command;

	private final int distance;

	public Instruction(String instruction) {
		String[] parameters = instruction.split(" ");
		command = Command.of(parameters[0]);
		distance = Integer.parseInt(parameters[1]);
	}

	public Command getCommand() {
		return command;
	}

	public int getDistance() {
		return distance;
	}
}
