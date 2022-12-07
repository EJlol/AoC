package nl.eleven.adventofcode.models.table;

import com.google.common.collect.Lists;
import nl.eleven.adventofcode.models.Position;
import nl.eleven.adventofcode.models.table.utils.RotateTable;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public record Table<T>(List<List<T>> contents) {

	public void debug() {
		contents.forEach(line -> {
			line.forEach(value -> System.out.print(value + " "));
			System.out.println();
		});
	}

	public Table<T> filterRows(Predicate<? super List<T>> predicate) {
		return new Table<>(contents.stream().filter(predicate).toList());
	}

	public Table<T> flipHorizontally() {
		return new Table<>(contents.stream().map(Lists::reverse).toList());
	}

	public Table<T> flipVertically() {
		return new Table<>(Lists.reverse(contents));
	}

	public T get(int x, int y) {
		return contents.get(y).get(x);
	}

	public List<T> getColumn(int index) {
		return contents.stream().map(innerList -> innerList.get(index)).toList();
	}

	public int getHeight() {
		return contents.size();
	}

	public Position getPositionByValue(T value) {
		int y = 0;
		for (List<T> row : contents) {
			int x = 0;
			for (T item : row) {
				if (value.equals(item)) {
					return new Position(x, y);
				}
				x++;
			}
			y++;
		}

		return null;
	}

	public List<T> getRow(int index) {
		return contents.get(index);
	}

	public int getWidth() {
		return !contents.isEmpty() ? contents.get(0).size() : 0;
	}

	public <R> List<R> mapRows(Function<? super List<T>, R> mapper) {
		return contents.stream().map(mapper).toList();
	}

	public void removeFirstColumn() {
		contents.forEach(innerList -> innerList.remove(0));
	}

	/**
	 * Rotates counter clockwise
	 *
	 * @return Rotates the whole table
	 */
	public Table<T> rotate() {
		return new Table<>(RotateTable.rotate(contents));
	}
}
