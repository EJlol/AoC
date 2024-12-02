package nl.eleven.adventofcode.models.table;

import com.google.common.collect.Lists;
import nl.eleven.adventofcode.helpers.list.ListHelper;
import nl.eleven.adventofcode.models.position.Position;
import nl.eleven.adventofcode.models.rect.Rectangle;
import nl.eleven.adventofcode.models.table.utils.RotateTable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public record Table<T>(List<List<T>> contents) {

	/**
	 * This method is used for debugging purposes.
	 * It prints the contents of the table to the console.
	 * Each row of the table is printed on a new line, and the values in the row are separated by spaces.
	 */
	public void debug() {
		contents.forEach(line -> {
			line.forEach(value -> System.out.print(value + " "));
			System.out.println();
		});
	}

	/**
	 * Filters the cells in the table based on a given predicate.
	 *
	 * @param predicate A BiPredicate that takes a cell value and its position in the table.
	 *                  The predicate should return true for cells that should be included in the result.
	 * @return A list of cell values for which the predicate returned true. The order of the cells in the list
	 * corresponds to the order they appear in the table (row by row from top to bottom, and within each row
	 * from left to right).
	 */
	public List<T> filterCells(BiPredicate<T, Position> predicate) {
		List<T> result = new ArrayList<>();
		for (int y = 0; y < contents.size(); y++) {
			for (int x = 0; x < contents.get(y).size(); x++) {
				T item = contents.get(y).get(x);
				if (predicate.test(item, new Position(x, y))) {
					result.add(item);
				}
			}
		}
		return result;
	}

	/**
	 * Filters the rows in the table based on a given predicate.
	 *
	 * @param predicate A Predicate that takes a list of cell values (a row in the table).
	 *                  The predicate should return true for rows that should be included in the result.
	 * @return A new Table where only the rows for which the predicate returned true are included.
	 */
	public Table<T> filterRows(Predicate<? super List<T>> predicate) {
		return new Table<>(contents.stream().filter(predicate).toList());
	}

	/**
	 * Flips the table horizontally.
	 *
	 * @return A new Table where the order of the columns is reversed.
	 */
	public Table<T> flipHorizontally() {
		return new Table<>(contents.stream().map(Lists::reverse).toList());
	}

	/**
	 * Flips the table vertically.
	 *
	 * @return A new Table where the order of the rows is reversed.
	 */
	public Table<T> flipVertically() {
		return new Table<>(Lists.reverse(contents));
	}

	/**
	 * Gets the value of the cell at the given position in the table.
	 *
	 * @param x The x-coordinate of the cell.
	 * @param y The y-coordinate of the cell.
	 * @return The value of the cell at the given position, or null if the position is outside the bounds of the table.
	 */
	public T get(int x, int y) {
		if (y < 0 || y >= getHeight() || x < 0 || x >= getWidth()) {
			return null;
		}
		return contents.get(y).get(x);
	}

	/**
	 * Retrieves all positions of a given value in the table.
	 *
	 * @param value The value to find in the table.
	 * @return A list of positions of all occurrences of the value in the table, each represented as a Position object.
	 * The positions are determined by scanning the table row by row from top to bottom, and within each row from left to right.
	 * If the value is not found in the table, an empty list is returned.
	 */
	public List<Position> getAllPositionsByValue(T value) {
		List<Position> result = new ArrayList<>();
		for (int y = 0; y < contents.size(); y++) {
			for (int x = 0; x < contents.get(y).size(); x++) {
				if (value.equals(contents.get(y).get(x))) {
					result.add(new Position(x, y));
				}
			}
		}
		return result;
	}

	/**
	 * Retrieves a list of cells that are located to the east of a given position in the table.
	 *
	 * @param position The position from which to start looking for cells to the east.
	 * @return A list of cells located to the east of the given position. The cells are ordered from west to east.
	 * If the position is at the easternmost column or outside the bounds of the table, an empty list is returned.
	 */
	public List<T> getCellsEastOfPosition(Position position) {
		return getRow(position.y()).subList(position.x() + 1, getWidth());
	}

	/**
	 * Retrieves a list of cells that are located to the north of a given position in the table.
	 *
	 * @param position The position from which to start looking for cells to the north.
	 * @return A list of cells located to the north of the given position. The cells are ordered from south to north.
	 * If the position is at the northernmost row or outside the bounds of the table, an empty list is returned.
	 */
	public List<T> getCellsNorthOfPosition(Position position) {
		return ListHelper.reverse(getColumn(position.x()).subList(0, position.y()));
	}

	/**
	 * Retrieves a list of cells that are located to the south of a given position in the table.
	 *
	 * @param position The position from which to start looking for cells to the south.
	 * @return A list of cells located to the south of the given position. The cells are ordered from north to south.
	 * If the position is at the southernmost row or outside the bounds of the table, an empty list is returned.
	 */
	public List<T> getCellsSouthOfPosition(Position position) {
		return getColumn(position.x()).subList(position.y() + 1, getHeight());
	}

	/**
	 * Retrieves a list of cells that are located to the west of a given position in the table.
	 *
	 * @param position The position from which to start looking for cells to the west.
	 * @return A list of cells located to the west of the given position. The cells are ordered from east to west.
	 * If the position is at the westernmost column or outside the bounds of the table, an empty list is returned.
	 */
	public List<T> getCellsWestOfPosition(Position position) {
		return ListHelper.reverse(getRow(position.y()).subList(0, position.x()));
	}

	/**
	 * Retrieves a column from the table.
	 *
	 * @param index The index of the column to retrieve. The leftmost column has index 0.
	 * @return A list of cell values in the specified column. The cells are ordered from top to bottom.
	 * If the index is outside the bounds of the table, an empty list is returned.
	 */
	public List<T> getColumn(int index) {
		return contents.stream().map(innerList -> innerList.get(index)).toList();
	}

	/**
	 * Retrieves a list of rectangles from the table based on a given predicate.
	 *
	 * @param predicate A Predicate that takes a cell value.
	 *                  The predicate should return true for cells that should be included in the rectangle.
	 * @return A list of rectangles. Each rectangle represents a contiguous group of cells for which the predicate returned true.
	 * The rectangles are determined by scanning the table row by row from top to bottom, and within each row from left to right.
	 * If no group of cells satisfies the predicate, an empty list is returned.
	 */
	public List<ColumnSpan> getColumnSpansByPredicate(Predicate<T> predicate) {
		List<ColumnSpan> result = new ArrayList<>();
		for (int y = 0; y < contents.size(); y++) {
			ColumnSpan currentRowSpan = null;
			for (int x = 0; x < contents.get(y).size(); x++) {
				if (predicate.test(contents.get(y).get(x))) {
					if (currentRowSpan == null) {
						currentRowSpan = new ColumnSpan(y, x, 1);
						result.add(currentRowSpan);
					} else {
						currentRowSpan.growRight();
					}
				} else {
					currentRowSpan = null;
				}
			}
		}
		return result;
	}

	/**
	 * Retrieves the height of the table.
	 *
	 * @return The number of rows in the table.
	 */
	public int getHeight() {
		return contents.size();
	}

	/**
	 * Gets the value of the cell at the given position in the table, or a default value if the position is outside the
	 * bounds of the table.
	 *
	 * @param x            The x-coordinate of the cell.
	 * @param y            The y-coordinate of the cell.
	 * @param defaultValue The value to return if the position is outside the bounds of the table.
	 * @return The value of the cell at the given position, or the default value if the position is outside the bounds of
	 * the table.
	 */
	public T getOrDefault(int x, int y, T defaultValue) {
		T value = get(x, y);
		return value != null ? value : defaultValue;
	}

	/**
	 * Retrieves the position of a given value in the table.
	 *
	 * @param value The value to find in the table.
	 * @return The position of the first occurrence of the value in the table, represented as a Position object.
	 * The position is determined by scanning the table row by row from top to bottom, and within each row from left to right.
	 * If the value is not found in the table, null is returned.
	 */
	public Position getPositionByValue(T value) {
		for (int y = 0; y < contents.size(); y++) {
			int x = contents.get(y).indexOf(value);
			if (x != -1) {
				return new Position(x, y);
			}
		}
		return null;
	}

	/**
	 * Retrieves a row from the table.
	 *
	 * @param index The index of the row to retrieve. The topmost row has index 0.
	 * @return A list of cell values in the specified row. The cells are ordered from left to right.
	 * If the index is outside the bounds of the table, an IndexOutOfBoundsException is thrown.
	 */
	public List<T> getRow(int index) {
		return contents.get(index);
	}

	public List<T> getValuesFromColumnSpan(ColumnSpan columnSpan) {
		return getRow(columnSpan.getRow()).subList(columnSpan.getX(), columnSpan.getRightX());
	}

	public List<T> getValuesFromRectangle(Rectangle rectangle) {
		List<T> result = new ArrayList<>();
		for (int y = rectangle.getY(); y < rectangle.getY() + rectangle.getHeight(); y++) {
			if (y < 0 || y >= contents.size()) {
				continue;
			}
			int left = Math.max(0, rectangle.getX());
			int right = Math.min(rectangle.getX() + rectangle.getWidth(), contents.get(y).size());
			result.addAll(contents.get(y).subList(left, right));
		}
		return result;
	}

	/**
	 * Retrieves the width of the table.
	 *
	 * @return The number of columns in the table.
	 */
	public int getWidth() {
		return !contents.isEmpty() ? contents.getFirst().size() : 0;
	}

	/**
	 * Applies a given function to each cell in the table and stores the results in a 2D array.
	 *
	 * @param <T2>   The type of the elements in the result array.
	 * @param mapper A BiFunction that takes a cell value and its position in the table, and returns a new value of type T2.
	 *               The function is applied to each cell in the table.
	 * @param array  A 2D array where the results of the function are stored. The array should have the same dimensions as the table.
	 *               The order of the elements in the array corresponds to the order of the cells in the table
	 *               (row by row from top to bottom, and within each row from left to right).
	 */
	public <T2> void mapCellsToArray(BiFunction<T, Position, T2> mapper, T2[][] array) {
		for (int y = 0; y < contents.size(); y++) {
			for (int x = 0; x < contents.get(y).size(); x++) {
				array[x][y] = mapper.apply(contents.get(y).get(x), new Position(x, y));
			}
		}
	}

	/**
	 * Applies a given function to each cell in the table and stores the results in a list.
	 *
	 * @param <T2>   The type of the elements in the result list.
	 * @param mapper A BiFunction that takes a cell value and its position in the table, and returns a new value of type T2.
	 *               The function is applied to each cell in the table.
	 * @return A list where the results of the function are stored. The order of the elements in the list corresponds to the order of the cells in the table
	 * (row by row from top to bottom, and within each row from left to right).
	 */
	public <T2> List<T2> mapCellsToList(BiFunction<T, Position, T2> mapper) {
		List<T2> result = new ArrayList<>();
		for (int y = 0; y < contents.size(); y++) {
			for (int x = 0; x < contents.get(y).size(); x++) {
				result.add(mapper.apply(contents.get(y).get(x), new Position(x, y)));
			}
		}
		return result;
	}

	/**
	 * Applies a given function to each row in the table and stores the results in a list.
	 *
	 * @param <R>    The type of the elements in the result list.
	 * @param mapper A Function that takes a list of cell values (a row in the table), and returns a new value of type R.
	 *               The function is applied to each row in the table.
	 * @return A list where the results of the function are stored. The order of the elements in the list corresponds to the order of the rows in the table
	 * (from top to bottom).
	 */
	public <R> List<R> mapRows(Function<? super List<T>, R> mapper) {
		return contents.stream().map(mapper).toList();
	}

	/**
	 * Removes the first column from the table.
	 * This method modifies the table in place. After the method is called, the table will have one less column.
	 * The cells in the removed column are discarded.
	 * If the table is empty or has only one column, it will be empty after the method is called.
	 */
	public void removeFirstColumn() {
		contents.forEach(List::removeFirst);
	}

	/**
	 * Rotates clockwise
	 *
	 * @return Rotates the whole table
	 */
	public Table<T> rotate() {
		return new Table<>(RotateTable.rotate(contents));
	}
}
