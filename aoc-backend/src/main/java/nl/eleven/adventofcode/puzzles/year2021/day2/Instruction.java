package nl.eleven.adventofcode.puzzles.year2021.day2;

class Instruction {

	private final Command command;

	private final int distance;

	Instruction(String instruction) {
		String[] parameters = instruction.split(" ");
		command = Command.of(parameters[0]);
		distance = Integer.parseInt(parameters[1]);
	}

	Command getCommand() {
		return command;
	}

	int getDistance() {
		return distance;
	}
}
