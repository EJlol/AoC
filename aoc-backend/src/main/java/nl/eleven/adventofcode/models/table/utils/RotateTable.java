package nl.eleven.adventofcode.models.table.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RotateTable {

	private RotateTable() {

	}

	/**
	 * Rotates the given 2D list (table) 90 degrees clockwise.
	 *
	 * @param <T>        The type of the elements in the table.
	 * @param inputTable The original table to be rotated. It is a 2D list where each inner list represents a row in the table.
	 * @return A new 2D list representing the rotated table. Each inner list in the returned list represents a row in the rotated table.
	 * The first inner list in the returned list corresponds to the first column in the original table,
	 * the second inner list corresponds to the second column, and so on.
	 * The elements in each inner list are in the same order as in the corresponding column in the original table.
	 */
	public static <T> List<List<T>> rotate(List<List<T>> inputTable) {
		List<List<T>> outputTable = new ArrayList<>();
		for (int column = 0; column < inputTable.getFirst().size(); column++) {
			List<T> outputInnerList = new ArrayList<>();
			for (List<T> row : inputTable) {
				outputInnerList.add(row.get(column));
			}
			outputTable.add(outputInnerList);
		}
		return outputTable;
	}

	/**
	 * Rotates the given 2D list (table) 90 degrees counter-clockwise.
	 *
	 * @param <T>        The type of the elements in the table.
	 * @param inputTable The original table to be rotated. It is a 2D list where each inner list represents a row in the table.
	 * @return A new 2D list representing the rotated table. Each inner list in the returned list represents a row in the rotated table.
	 * The first inner list in the returned list corresponds to the last column in the original table,
	 * the second inner list corresponds to the second last column, and so on.
	 * The elements in each inner list are in the same order as in the corresponding column in the original table, but reversed.
	 */
	public static <T> List<List<T>> rotateCounterClockwise(List<List<T>> inputTable) {
		Collections.reverse(inputTable);
		return rotate(inputTable);
	}
}
