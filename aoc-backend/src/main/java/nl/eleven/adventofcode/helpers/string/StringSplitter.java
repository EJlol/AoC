package nl.eleven.adventofcode.helpers.string;

import com.google.common.collect.Lists;
import nl.eleven.adventofcode.models.pair.SimplePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class StringSplitter {

	private StringSplitter() {

	}

	/**
	 * "AABBCC" -> ["AAB", "BCC"]
	 */
	public static SimplePair<String> partitionInTwo(String input) {
		int partitionIndex = input.length() / 2;
		String leftInput = input.substring(0, partitionIndex);
		String rightInput = input.substring(partitionIndex);
		return new SimplePair<>(leftInput, rightInput);
	}

	/**
	 * This method splits the input string at any of the strings in the provided list.
	 * It iterates over the indices of the input string, and for each index, it generates a substring of the input string
	 * starting from the current index. It then checks if this substring starts with any of the strings in the provided list.
	 * If it does, it adds the substring of the input string from the start index to the current index to the result list,
	 * updates the start index, and continues with the next index. After all indices have been processed, it adds the
	 * remaining substring of the input string from the start index to the end of the input string to the result list.
	 * Example: splitAtAny("abacada", List.of("a", "c")) returns ["", "b", "", "", "d", ""].
	 *
	 * @param input         The string to be split.
	 * @param searchStrings The list of strings at which to split the input string.
	 * @return A list of substrings of the input string, split at any of the strings in the provided list.
	 */
	public static List<String> splitAtAny(String input, List<String> searchStrings) {
		List<String> result = new ArrayList<>();
		int startIndex = 0;
		for (int index = 0; index < input.length(); index++) {
			String substring = input.substring(index);
			Optional<String> match = searchStrings.stream().filter(substring::startsWith).findFirst();
			if (match.isPresent()) {
				result.add(input.substring(startIndex, index));
				index += match.get().length();
				startIndex = index;
			}
		}
		result.add(input.substring(startIndex));
		return result;
	}

	/**
	 * Splits by character.
	 * "AA,BB,CC" -> ["AA", "BB", "CC"] with ',' as separator
	 */
	public static List<String> splitAtCharacter(String input, Character separator) {
		return Lists.newArrayList(input.split(Pattern.quote(separator.toString())));
	}

	/**
	 * Splits by character.
	 * "AA,BB" -> ["AA", "BB"] with ',' as separator
	 */
	public static SimplePair<String> splitAtCharacterInTwo(String input, Character character) {
		List<String> partitions = splitAtCharacter(input, character);
		if (partitions.size() != 2) {
			throw new IllegalArgumentException("the amount of partitions does not equal 2. Use .splitAtCharacter() instead");
		}
		return new SimplePair<>(partitions.get(0), partitions.get(1));
	}

	/**
	 * Splits by a string.
	 * "AA, BB, CC" -> ["AA", "BB", "CC"] with ", " as separator
	 */
	public static List<String> splitAtString(String input, String splitString) {
		return Lists.newArrayList(input.split(Pattern.quote(splitString)));
	}

	public static List<String> splitAtWhitespace(String input) {
		return Lists.newArrayList(input.trim().split("\\s+"));
	}
}
