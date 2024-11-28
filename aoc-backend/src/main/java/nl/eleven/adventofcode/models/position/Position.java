package nl.eleven.adventofcode.models.position;

import nl.eleven.adventofcode.models.rect.Rectangle;

public record Position(int x, int y) {

	public static final Position ZERO = new Position(0, 0);

	public static final Position ONE = new Position(1, 1);

	public static final Position NORTH = new Position(0, -1);

	public static final Position NORTHEAST = new Position(1, -1);

	public static final Position EAST = new Position(1, 0);

	public static final Position SOUTHEAST = new Position(1, 1);

	public static final Position SOUTH = new Position(0, 1);

	public static final Position SOUTHWEST = new Position(-1, 1);

	public static final Position WEST = new Position(-1, 0);

	public static final Position NORTHWEST = new Position(-1, -1);

	public static Position fromString(String stringPosition) {
		String[] splitString = stringPosition.split(",");
		return new Position(Integer.parseInt(splitString[0]), Integer.parseInt(splitString[1]));
	}

	public int manhattanDistance(Position other) {
		return Math.abs(this.x() - other.x()) + Math.abs(this.y() - other.y());
	}

	public Position min(Position other) {
		return new Position(x() - other.x(), y() - other.y());
	}

	public Position plus(Position other) {
		return new Position(x() + other.x(), y() + other.y());
	}

	public Rectangle toRectangle() {
		return new Rectangle(x(), y(), 1, 1);
	}

	@Override
	public String toString() {
		return String.format("(%d, %d)", x(), y());
	}
}
