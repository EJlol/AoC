package nl.eleven.adventofcode.models.position;

public record Position3D(int x, int y, int z) {

	public static final Position3D NORTH = new Position3D(0, -1, 0);

	public static final Position3D NORTHEAST = new Position3D(1, -1, 0);

	public static final Position3D EAST = new Position3D(1, 0, 0);

	public static final Position3D SOUTHEAST = new Position3D(1, 1, 0);

	public static final Position3D SOUTH = new Position3D(0, 1, 0);

	public static final Position3D SOUTHWEST = new Position3D(-1, 1, 0);

	public static final Position3D WEST = new Position3D(-1, 0, 0);

	public static final Position3D NORTHWEST = new Position3D(-1, -1, 0);

	public static final Position3D FORWARDS = new Position3D(0, 0, 1);

	public static final Position3D BACKWARDS = new Position3D(0, 0, -1);

	public static Position3D fromString(String stringPosition) {
		String[] splitString = stringPosition.split(",");
		return new Position3D(Integer.parseInt(splitString[0]), Integer.parseInt(splitString[1]), Integer.parseInt(splitString[2]));
	}

	public int manhattanDistance(Position3D other) {
		return Math.abs(this.x() - other.x()) + Math.abs(this.y() - other.y()) + Math.abs(this.z() - other.z());
	}

	public Position3D min(Position3D other) {
		return new Position3D(x() - other.x(), y() - other.y(), z() - other.z());
	}

	public Position3D plus(Position3D other) {
		return new Position3D(x() + other.x(), y() + other.y(), z() + other.z());
	}

	@Override
	public String toString() {
		return String.format("(%d, %d, %d)", x(), y(), z());
	}
}
