package nl.eleven.adventofcode.models.direction;

import nl.eleven.adventofcode.models.position.Position;

public enum Direction {
	CENTER(Position.ZERO),
	NORTH(Position.NORTH),
	SOUTH(Position.SOUTH),
	WEST(Position.WEST),
	EAST(Position.EAST);

	final Position position;

	Direction(Position position) {
		this.position = position;
	}

	public static Direction of(String value) {
		return switch (value.toLowerCase()) {
			case "^" -> NORTH;
			case "v" -> SOUTH;
			case "<" -> WEST;
			case ">" -> EAST;
			default -> CENTER;
		};
	}

	public Position getPosition() {
		return position;
	}
}
