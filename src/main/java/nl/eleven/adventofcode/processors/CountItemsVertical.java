package nl.eleven.adventofcode.processors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class CountItemsVertical<T> {

	public List<Map<T, Integer>> process(Stream<List<T>> stream) {
		List<Map<T, Integer>> result = new ArrayList<>();

		stream.forEach(list -> {
			AtomicInteger columnIndex = new AtomicInteger();
			list.forEach(item -> {
				if (columnIndex.get() >= result.size()) {
					// For every column we need to add a hashmap
					result.add(new HashMap<>());
				}
				// Retrieve the correct hashmap
				Map<T, Integer> columnMap = result.get(columnIndex.get());

				// Count the value
				if (columnMap.containsKey(item)) {
					columnMap.put(item, columnMap.get(item) + 1);
				} else {
					columnMap.put(item, 1);
				}

				columnIndex.getAndIncrement();
			});
		});
		return result;
	}
}
