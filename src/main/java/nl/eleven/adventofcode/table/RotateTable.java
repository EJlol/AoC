package nl.eleven.adventofcode.table;

import java.util.ArrayList;
import java.util.List;

public class RotateTable<T> {

	public List<List<T>> map(List<List<T>> inputTable) {
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
