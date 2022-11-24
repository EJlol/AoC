package nl.eleven.adventofcode.readers;

import java.util.stream.Stream;

public class NumberReader implements PuzzleReader {

	private NumberReader() {

	}

	public static Stream<Integer> read(String url) {
		String body = PuzzleReader.getPuzzleContent(url);
		if (body != null) {
			return body.lines().map(Integer::valueOf);
		} else {
			return Stream.empty();
		}
	}
}
