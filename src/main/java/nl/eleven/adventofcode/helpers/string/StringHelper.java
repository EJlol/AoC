package nl.eleven.adventofcode.helpers.string;

import com.google.common.collect.Lists;
import nl.eleven.adventofcode.models.pair.SimplePair;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringHelper {

	private StringHelper() {

	}

	public static Set<Character> getUniqueCharacters(String input) {
		return input.chars().mapToObj(i -> (char) i).collect(Collectors.toSet());
	}

	public static SimplePair<String> partitionInTwo(String input) {
		int partitionIndex = input.length() / 2;
		String leftInput = input.substring(0, partitionIndex);
		String rightInput = input.substring(partitionIndex);
		return new SimplePair<>(leftInput, rightInput);
	}

	public static List<String> splitAtCharacter(String input, Character character) {
		return Lists.newArrayList(input.split(Pattern.quote(character.toString())));
	}

	public static SimplePair<String> splitAtCharacterInTwo(String input, Character character) {
		List<String> partitions = StringHelper.splitAtCharacter(input, character);
		if (partitions.size() != 2) {
			throw new IllegalArgumentException("the amount of partitions does not equal 2. Use .splitAtCharacter() instead");
		}
		return new SimplePair<>(partitions.get(0), partitions.get(1));
	}
}
