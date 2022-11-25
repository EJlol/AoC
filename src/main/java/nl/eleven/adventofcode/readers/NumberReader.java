package nl.eleven.adventofcode.readers;

import java.util.stream.Stream;

public class NumberReader {

	public Stream<Integer> parse(Stream<String> stream) {
		return stream.map(Integer::valueOf);
	}
}
