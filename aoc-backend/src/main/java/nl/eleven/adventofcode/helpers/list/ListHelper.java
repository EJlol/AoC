package nl.eleven.adventofcode.helpers.list;

import nl.eleven.adventofcode.models.pair.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListHelper {

	private ListHelper() {

	}

	public static <T> Map<T, Long> countEqualItems(List<T> input) {
		return input.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}

	public static int max(List<Integer> list) {
		if (list == null || list.isEmpty()) {
			return 0;
		}
		return Collections.max(list);
	}

	public static <T> List<T> max(List<T> list, int amount) {
		return list.stream()
				.sorted(Collections.reverseOrder())
				.limit(amount)
				.toList();
	}

	public static int min(List<Integer> list) {
		if (list == null || list.isEmpty()) {
			return 0;
		}
		return Collections.min(list);
	}

	public static <T> List<T> min(List<T> list, int amount) {
		return list.stream()
				.sorted()
				.limit(amount)
				.toList();
	}

	public static <T> List<T> reverse(List<T> list) {
		List<T> resultList = new ArrayList<>(list);
		Collections.reverse(resultList);
		return resultList;
	}

	public static <T, U> List<Pair<T, U>> zip(List<T> list1, List<U> list2) {
		if (list1.size() != list2.size()) {
			throw new IllegalArgumentException("Lists must be of equal size");
		}
		List<Pair<T, U>> result = new ArrayList<>();
		for (int i = 0; i < list1.size(); i++) {
			result.add(new Pair<>(list1.get(i), list2.get(i)));
		}
		return result;
	}
}
