package nl.eleven.adventofcode.helpers.inputmappers;

import java.util.List;

public class NumberMapper {

	private NumberMapper() {

	}

	public static List<Integer> map(List<String> input) {
		return input.stream().map(Integer::valueOf).toList();
	}
}
