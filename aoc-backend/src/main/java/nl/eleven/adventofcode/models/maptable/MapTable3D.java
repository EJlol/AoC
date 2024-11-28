package nl.eleven.adventofcode.models.maptable;

import nl.eleven.adventofcode.helpers.Position3DHelper;
import nl.eleven.adventofcode.models.position.Position3D;
import nl.eleven.adventofcode.models.rect.Cuboid;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class MapTable3D<T> {

	HashMap<Position3D, T> map;

	public MapTable3D() {
		this.map = new HashMap<>();
	}

	public void debug(int z) {
		List<Position3D> positions = map.keySet().stream().toList();
		int x1 = Position3DHelper.findFurthestWestCoordinate(positions);
		int x2 = Position3DHelper.findFurthestEastCoordinate(positions);
		int y1 = Position3DHelper.findFurthestNorthCoordinate(positions);
		int y2 = Position3DHelper.findFurthestSouthCoordinate(positions);

		for (int y = y1; y < y2; y++) {
			for (int x = x1; x < x2; x++) {
				T o = map.get(new Position3D(x, y, z));
				System.out.print(o != null ? o.toString().charAt(0) : '.');
			}
			System.out.println();
		}
	}

	public void fillCube(int x, int y, int z, int width, int height, int depth, T value) {
		for (int i = x; i < x + width; i++) {
			for (int j = y; j < y + height; j++) {
				for (int k = z; k < z + depth; k++) {
					plot(i, j, k, value);
				}
			}
		}
	}

	/**
	 * Fills the outside of the 3D map with a specified value.
	 * This method first calculates the boundaries of the 3D map by finding the furthest coordinates in each direction.
	 * It then starts from a position outside these boundaries and performs a breadth-first search (BFS), filling each unoccupied position with the specified value.
	 * The BFS stops when it has visited all positions within the calculated boundaries.
	 *
	 * @param value The value to fill the outside of the 3D map with.
	 */
	public void fillOutside(T value) {
		Cuboid boundaries = findBoundaries();

		Queue<Position3D> queue = new LinkedList<>();
		queue.add(new Position3D(boundaries.getX(), boundaries.getY(), boundaries.getZ()));

		while (!queue.isEmpty()) {
			Position3D p = queue.poll();
			if (p.x() >= boundaries.getX() && p.x() <= boundaries.getX() + boundaries.getWidth() &&
					p.y() >= boundaries.getY() && p.y() <= boundaries.getY() + boundaries.getHeight() &&
					p.z() >= boundaries.getZ() && p.z() <= boundaries.getZ() + boundaries.getDepth() &&
					!map.containsKey(p)) {
				plot(p, value);
				queue.addAll(p.getNeighbours());
			}
		}
	}

	public Cuboid findBoundaries() {
		List<Position3D> positions = map.keySet().stream().toList();
		int x1 = Position3DHelper.findFurthestWestCoordinate(positions) - 2;
		int x2 = Position3DHelper.findFurthestEastCoordinate(positions) + 2;
		int y1 = Position3DHelper.findFurthestNorthCoordinate(positions) - 2;
		int y2 = Position3DHelper.findFurthestSouthCoordinate(positions) + 2;
		int z1 = Position3DHelper.findFurthestBackwardsCoordinate(positions) - 2;
		int z2 = Position3DHelper.findFurthestForwardsCoordinate(positions) + 2;

		int width = x2 - x1;
		int height = y2 - y1;
		int depth = z2 - z1;

		return new Cuboid(x1, y1, z1, width, height, depth);
	}

	public void forEach(BiConsumer<? super Position3D, ? super T> consumer) {
		map.forEach(consumer);
	}

	public T get(Position3D p) {
		return map.get(p);
	}

	public int getHeight() {
		List<Position3D> positions = map.keySet().stream().toList();
		int y1 = Position3DHelper.findFurthestNorthCoordinate(positions);
		int y2 = Position3DHelper.findFurthestSouthCoordinate(positions);
		return y2 - y1;
	}

	public T getOrDefault(int x, int y, int z, T value) {
		return map.getOrDefault(new Position3D(x, y, z), value);
	}

	public int getWidth() {
		List<Position3D> positions = map.keySet().stream().toList();
		int x1 = Position3DHelper.findFurthestWestCoordinate(positions);
		int x2 = Position3DHelper.findFurthestEastCoordinate(positions);
		return x2 - x1;
	}

	public boolean isEmpty(int x, int y, int z) {
		Position3D position = new Position3D(x, y, z);
		return !map.containsKey(position);
	}

	public boolean isEmpty(Position3D position) {
		return !map.containsKey(position);
	}

	public void plot(int x, int y, int z, T value) {
		plot(new Position3D(x, y, z), value);
	}

	public void plot(Position3D p, T value) {
		map.put(p, value);
	}

	public Stream<Map.Entry<Position3D, T>> streamEntries() {
		return map.entrySet().stream();
	}
}
