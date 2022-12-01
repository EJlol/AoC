package nl.eleven.adventofcode.mappers;

import nl.eleven.adventofcode.table.RotateTable;

import java.util.ArrayList;
import java.util.List;

public class CharacterTableMapper {

	public List<List<Character>> map(List<String> input) {
		List<List<Character>> table = new ArrayList<>();

		input.forEach(line -> {
			List<Character> characterList = line.chars().mapToObj(c -> (char) c).toList();
			table.add(characterList);
		});

		return table;
	}

	public List<List<Character>> mapAndRotate(List<String> input) {
		List<List<Character>> inputTable = this.map(input);
		return new RotateTable<Character>().map(inputTable);
	}
}
