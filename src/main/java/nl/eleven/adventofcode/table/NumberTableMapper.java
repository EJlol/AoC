package nl.eleven.adventofcode.table;

import nl.eleven.adventofcode.table.utils.RotateTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberTableMapper {

	private NumberTableMapper() {

	}

	public static List<List<Integer>> map(List<String> input, String regex) {
		List<List<Integer>> table = new ArrayList<>();

		input.forEach(line -> {
			List<Integer> innerList = Arrays.stream(line.split(regex))
					.map(Integer::parseInt)
					.toList();
			table.add(innerList);
		});

		return table;
	}

	public static List<List<Integer>> mapAndRotate(List<String> input, String regex) {
		List<List<Integer>> inputTable = NumberTableMapper.map(input, regex);
		return RotateTable.rotate(inputTable);
	}
}
