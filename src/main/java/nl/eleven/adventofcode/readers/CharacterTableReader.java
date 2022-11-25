package nl.eleven.adventofcode.readers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CharacterTableReader {

	public Stream<List<Character>> parse(Stream<String> stream) {
		List<List<Character>> table = new ArrayList<>();

		stream.forEach(line -> {
			List<Character> characterList = line.chars().mapToObj(c -> (char) c).toList();
			table.add(characterList);
		});

		return table.stream();
	}
}
