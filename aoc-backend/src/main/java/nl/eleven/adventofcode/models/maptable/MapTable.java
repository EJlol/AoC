package nl.eleven.adventofcode.models.maptable;

import nl.eleven.adventofcode.helpers.PositionHelper;
import nl.eleven.adventofcode.models.position.Position;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class MapTable<T> {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(MapTable.class);

	int floorHeight = 0;

	T floorValue = null;

	HashMap<Position, T> map;

	public MapTable() {
		this.map = new HashMap<>();
	}

	public void debug() {
		List<Position> positions = map.keySet().stream().toList();
		int x1 = PositionHelper.findFurthestWestCoordinate(positions);
		int x2 = PositionHelper.findFurthestEastCoordinate(positions);
		int y1 = PositionHelper.findFurthestNorthCoordinate(positions);
		int y2 = PositionHelper.findFurthestSouthCoordinate(positions);

		if (floorValue != null) {
			y2 = floorHeight + 3;
		}

		for (int y = y1; y <= y2; y++) {
			for (int x = x1; x <= x2; x++) {
				T o = map.get(new Position(x, y));
				if (floorValue != null && y >= floorHeight) {
					o = floorValue;
				}

				logger.debug(Character.toString(o != null ? o.toString().charAt(0) : '.'));
			}
			logger.debug("");
		}
	}

	public void drawHorizontalLine(int x1, int x2, int y, T value) {
		int minX = Math.min(x1, x2);
		int maxX = Math.max(x1, x2);
		for (int x = minX; x <= maxX; x++) {
			plot(x, y, value);
		}
	}

	public void drawLine(int x1, int y1, int x2, int y2, T value) {
		if (x1 - x2 != 0 && y1 - y2 != 0) {
			throw new IllegalArgumentException("Line should either be horizontal or vertical");
		}
		boolean isHorizontal = (y1 - y2) == 0;
		if (isHorizontal) {
			drawHorizontalLine(x1, x2, y1, value);
		} else {
			drawVerticalLine(y1, y2, x1, value);
		}
	}

	public void drawVerticalLine(int y1, int y2, int x, T value) {
		int minY = Math.min(y1, y2);
		int maxY = Math.max(y1, y2);
		for (int y = minY; y <= maxY; y++) {
			plot(x, y, value);
		}
	}

	public void fillRect(int x, int y, int width, int height, T value) {
		for (int i = x; i < x + width; i++) {
			for (int j = y; j < y + height; j++) {
				plot(x, y, value);
			}
		}
	}

	public void forEach(BiConsumer<? super Position, ? super T> consumer) {
		map.forEach(consumer);
	}

	public int getHeight() {
		List<Position> positions = map.keySet().stream().toList();
		int y1 = PositionHelper.findFurthestNorthCoordinate(positions);
		int y2 = PositionHelper.findFurthestSouthCoordinate(positions);
		return y2 - y1;
	}

	public T getOrDefault(Position p, T value) {
		return map.getOrDefault(p, value);
	}

	public T getOrDefault(int x, int y, T value) {
		return map.getOrDefault(new Position(x, y), value);
	}

	public int getWidth() {
		List<Position> positions = map.keySet().stream().toList();
		int x1 = PositionHelper.findFurthestWestCoordinate(positions);
		int x2 = PositionHelper.findFurthestEastCoordinate(positions);
		return x2 - x1;
	}

	public boolean isEmpty(int x, int y) {
		if (floorValue != null && y >= floorHeight) {
			return false;
		}

		Position position = new Position(x, y);
		return !map.containsKey(position);
	}

	public void plot(int x, int y, T value) {
		plot(new Position(x, y), value);
	}

	public void plot(Position p, T value) {
		if (floorValue != null && p.y() >= floorHeight) {
			return;
		}
		map.put(p, value);
	}

	public void setFloorHeight(int y, T value) {
		this.floorHeight = y;
		this.floorValue = value;
	}

	public int size() {
		return map.size();
	}

	public Stream<T> stream() {
		return map.values().stream();
	}
}
