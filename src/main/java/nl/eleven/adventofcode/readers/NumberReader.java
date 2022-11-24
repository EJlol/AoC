package nl.eleven.adventofcode.readers;

import java.util.stream.Stream;

public class NumberReader implements PuzzleReader {

	public NumberReader() {
		throw new UnsupportedOperationException();
	}

	public Stream<Integer> read(String url) {
		String body = PuzzleReader.getPuzzleContent(url);
		if (body != null) {
			return body.lines().map(Integer::valueOf);
		} else {
			return Stream.empty();
		}
	}
}
