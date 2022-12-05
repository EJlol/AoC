package nl.eleven.adventofcode.models.table;

import nl.eleven.adventofcode.models.Position;
import nl.eleven.adventofcode.models.table.utils.RotateTable;

import java.util.List;
import java.util.function.Predicate;

public record Table<T>(List<List<T>> contents) {

	public int getHeight() {
		return contents.size();
	}

	public int getWidth() {
		return !contents.isEmpty() ? contents.get(0).size() : 0;
	}

	public Table<T> rotate() {
		return new Table<>(RotateTable.rotate(contents));
	}

	public Table<T> filterRows(Predicate<? super List<T>> predicate) {
		return new Table<>(contents.stream().filter(predicate).toList());
	}

	public List<T> getRow(int index) {
		return contents.get(index);
	}

	public List<T> getColumn(int index) {
		return contents.stream().map(innerList -> innerList.get(index)).toList();
	}

	public Position getPositionByValue(T value) {
		int y = 0;
		for(List<T> row : contents) {
			int x = 0;
			for(T item : row) {
				if (value.equals(item)) {
					return new Position(x, y);
				}
				x++;
			}
			y++;
		}

		return null;
	}

	public T get(int x, int y) {
		return contents.get(y).get(x);
	}
}
