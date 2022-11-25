package nl.eleven.adventofcode.readers;

import java.util.stream.Stream;

public class NumberReader implements PuzzleReader {

	public NumberReader() {
		throw new UnsupportedOperationException();
	}

	public Stream<Integer> parse(Stream<String> stream) {
		return stream.map(Integer::valueOf);
	}
}
