package nl.eleven.adventofcode.mappers;

import java.util.List;

public class NumberMapper {

	private NumberMapper() {

	}

	public static List<Integer> map(List<String> input) {
		return input.stream().map(Integer::valueOf).toList();
	}
}
