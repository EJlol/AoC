package nl.eleven.adventofcode.models.table.utils;

import java.util.ArrayList;
import java.util.List;

public class RotateTable {

	private RotateTable() {

	}

	public static <T> List<List<T>> rotate(List<List<T>> inputTable) {
		List<List<T>> outputTable = new ArrayList<>();
		for (int column = 0; column < inputTable.get(0).size(); column++) {
			int finalColumn = column;
			List<T> outputInnerList = new ArrayList<>(inputTable.stream()
					.map(innerList -> innerList.get(finalColumn))
					.toList());
			outputTable.add(outputInnerList);
		}

		return outputTable;
	}
}
