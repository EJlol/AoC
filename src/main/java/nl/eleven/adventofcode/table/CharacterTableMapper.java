package nl.eleven.adventofcode.table;

import java.util.ArrayList;
import java.util.List;

public class CharacterTableMapper {

	private CharacterTableMapper() {

	}

	public static Table<Character> map(List<String> input) {
		List<List<Character>> tableInput = new ArrayList<>();

		input.forEach(line -> {
			List<Character> characterList = line.chars().mapToObj(c -> (char) c).toList();
			tableInput.add(characterList);
		});

		return new Table<>(tableInput);
	}
}
