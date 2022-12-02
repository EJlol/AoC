package nl.eleven.adventofcode.helpers.groupingby;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountItems<T> {

	public Map<T, Long> count(List<T> input) {
		return input.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}
}
