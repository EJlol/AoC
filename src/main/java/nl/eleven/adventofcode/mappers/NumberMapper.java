package nl.eleven.adventofcode.mappers;

import java.util.List;

public class NumberMapper {

	public List<Integer> map(List<String> input) {
		return input.stream().map(Integer::valueOf).toList();
	}
}
