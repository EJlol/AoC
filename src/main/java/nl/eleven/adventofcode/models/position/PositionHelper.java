package nl.eleven.adventofcode.models.position;

import java.util.List;

public class PositionHelper {

	private PositionHelper() {

	}

	public static int findFurthestEastCoordinate(List<Position> list) {
		return list.stream().mapToInt(Position::x).max().orElse(0);
	}

	public static int findFurthestNorthCoordinate(List<Position> list) {
		return list.stream().mapToInt(Position::y).min().orElse(0);
	}

	public static int findFurthestSouthCoordinate(List<Position> list) {
		return list.stream().mapToInt(Position::y).max().orElse(0);
	}

	public static int findFurthestWestCoordinate(List<Position> list) {
		return list.stream().mapToInt(Position::x).min().orElse(0);
	}
}
