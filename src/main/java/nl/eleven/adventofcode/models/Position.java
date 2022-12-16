package nl.eleven.adventofcode.models;

public record Position(int x, int y) {

	public static final Position NORTH = new Position(0, -1);

	public static final Position NORTHEAST = new Position(1, -1);

	public static final Position EAST = new Position(1, 0);

	public static final Position SOUTHEAST = new Position(1, 1);

	public static final Position SOUTH = new Position(0, 1);

	public static final Position SOUTHWEST = new Position(-1, 1);

	public static final Position WEST = new Position(-1, 0);

	public static final Position NORTHWEST = new Position(-1, -1);

	public Position min(Position other) {
		return new Position(x() - other.x(), y() - other.y());
	}

	public Position plus(Position other) {
		return new Position(x() + other.x(), y() + other.y());
	}

	@Override
	public String toString() {
		return String.format("(%d, %d)", x(), y());
	}
}
