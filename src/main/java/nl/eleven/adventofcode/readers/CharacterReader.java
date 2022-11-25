package nl.eleven.adventofcode.readers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CharacterReader implements PuzzleReader {

	public Stream<List<Character>> read(String url) {
		String body = PuzzleReader.getPuzzleContent(url);
		List<List<Character>> table = new ArrayList<>();

		if (body != null) {
			body.lines().forEach(line -> {
				List<Character> characterList = line.chars().mapToObj(c -> (char) c).toList();
				table.add(characterList);
			});
			return table.stream();
		} else {
			return Stream.empty();
		}
	}
}
