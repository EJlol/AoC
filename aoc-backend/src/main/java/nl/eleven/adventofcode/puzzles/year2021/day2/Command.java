package nl.eleven.adventofcode.puzzles.year2021.day2;

enum Command {
	FORWARD,
	DOWN,
	UP;

	static Command of(String command) {
		return switch (command.toLowerCase()) {
			case "forward" -> FORWARD;
			case "down" -> DOWN;
			case "up" -> UP;
			default -> null;
		};
	}
}
