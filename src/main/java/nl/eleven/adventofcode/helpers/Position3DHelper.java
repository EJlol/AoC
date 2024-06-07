package nl.eleven.adventofcode.helpers;

import nl.eleven.adventofcode.models.position.Position3D;

import java.util.List;

public class Position3DHelper {

	private Position3DHelper() {

	}

	public static int findFurthestBackwardsCoordinate(List<Position3D> list) {
		return list.stream().mapToInt(Position3D::z).min().orElse(0);
	}

	public static int findFurthestEastCoordinate(List<Position3D> list) {
		return list.stream().mapToInt(Position3D::x).max().orElse(0);
	}

	public static int findFurthestForwardsCoordinate(List<Position3D> list) {
		return list.stream().mapToInt(Position3D::z).max().orElse(0);
	}

	public static int findFurthestNorthCoordinate(List<Position3D> list) {
		return list.stream().mapToInt(Position3D::y).min().orElse(0);
	}

	public static int findFurthestSouthCoordinate(List<Position3D> list) {
		return list.stream().mapToInt(Position3D::y).max().orElse(0);
	}

	public static int findFurthestWestCoordinate(List<Position3D> list) {
		return list.stream().mapToInt(Position3D::x).min().orElse(0);
	}
}
