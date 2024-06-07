package nl.eleven.adventofcode.helpers.string;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringHelper {

	private StringHelper() {

	}

	/**
	 * This method checks if the input string contains any of the strings in the provided list.
	 * Example: containsAny("abcd", List.of("b", "d")) returns "b".
	 *
	 * @param input         The string to be checked.
	 * @param searchStrings The list of strings to search for in the input string.
	 * @return The first string from the list found in the input string, or null if none are found.
	 */
	public static String containsAny(String input, List<String> searchStrings) {
		return searchStrings.stream()
				.filter(input::contains)
				.findFirst()
				.orElse(null);
	}

	/**
	 * This method finds the leftmost character in the input string that satisfies the provided predicate.
	 * Example: findLeftMostCharacter("abc", c -> c == 'b' || c == 'c') returns 'b'.
	 *
	 * @param input     The string to be checked.
	 * @param predicate The condition to be checked for each character in the string.
	 * @return The first character from the left that satisfies the predicate, or null if none does.
	 */
	public static Character findLeftMostCharacter(String input, Predicate<Character> predicate) {
		return input.chars()
				.mapToObj(c -> (char) c)
				.filter(predicate)
				.findFirst()
				.orElse(null);
	}

	/**
	 * This method finds the leftmost substring in the input string that satisfies the provided predicate.
	 * It generates all possible substrings of the input string, starting from the leftmost character and moving right,
	 * and applies the predicate to each substring. The first substring that satisfies the predicate is returned.
	 * If no substring satisfies the predicate, the method returns null.
	 *
	 * @param input     The string to be checked.
	 * @param predicate The condition to be checked for each substring in the string.
	 * @return The first substring from the left that satisfies the predicate, or null if none does.
	 */
	public static String findLeftMostString(String input, Predicate<String> predicate) {
		return IntStream.range(0, input.length())
				.boxed()
				.flatMap(i -> IntStream.rangeClosed(i + 1, input.length())
						.mapToObj(j -> input.substring(i, j)))
				.filter(predicate)
				.findFirst()
				.orElse(null);
	}

	/**
	 * This method finds the rightmost character in the input string that satisfies the provided predicate.
	 * Example: findRightMostCharacter("abc", c -> c == 'b' || c == 'c') returns 'c'.
	 *
	 * @param input     The string to be checked.
	 * @param predicate The condition to be checked for each character in the string.
	 * @return The first character from the right that satisfies the predicate, or null if none does.
	 */
	public static Character findRightMostCharacter(String input, Predicate<Character> predicate) {
		return new StringBuilder(input).reverse().chars()
				.mapToObj(c -> (char) c)
				.filter(predicate)
				.findFirst()
				.orElse(null);
	}

	/**
	 * This method finds the rightmost substring in the input string that satisfies the provided predicate.
	 * It generates all possible substrings of the input string, starting from the rightmost character and moving left,
	 * and applies the predicate to each substring. The first substring that satisfies the predicate is returned.
	 * If no substring satisfies the predicate, the method returns null.
	 *
	 * @param input     The string to be checked.
	 * @param predicate The condition to be checked for each substring in the string.
	 * @return The first substring from the right that satisfies the predicate, or null if none does.
	 */
	public static String findRightMostString(String input, Predicate<String> predicate) {
		return IntStream.rangeClosed(0, input.length())
				.boxed()
				.flatMap(i -> IntStream.range(i, input.length())
						.mapToObj(j -> input.substring(i, j + 1)))
				.filter(predicate)
				.reduce((_, second) -> second)
				.orElse(null);
	}

	/**
	 * This method calculates the frequency of each character in the input string.
	 * It converts the input string into a stream of characters, and then groups them by their identity,
	 * counting the occurrences of each character. The result is a map where the keys are the characters
	 * from the input string and the values are the frequencies of these characters.
	 * Example: getCharacterFrequencies("abca") returns {a=2, b=1, c=1}.
	 *
	 * @param input The string whose character frequencies are to be calculated.
	 * @return A map with characters as keys and their frequencies as values.
	 */
	public static Map<Character, Long> getCharacterFrequencies(String input) {
		return input.chars()
				.mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}

	/**
	 * This method converts the input string into a list of characters.
	 * It converts the input string into a stream of characters, and then maps each integer value to its corresponding
	 * char value. The result is a list of characters from the input string.
	 * Example: getCharacters("abca") returns ['a', 'b', 'c', 'a'].
	 *
	 * @param input The string to be converted into a list of characters.
	 * @return A list of characters from the input string.
	 */
	public static List<Character> getCharacters(String input) {
		return input.chars().mapToObj(i -> (char) i).toList();
	}

	/**
	 * This method retrieves all unique characters from the input string.
	 * It converts the input string into a stream of characters, and then collects them into a set,
	 * effectively removing any duplicate characters. The result is a set of unique characters from the input string.
	 * Example: getUniqueCharacters("abca") returns {'a', 'b', 'c'}.
	 *
	 * @param input The string from which unique characters are to be retrieved.
	 * @return A set of unique characters from the input string.
	 */
	public static Set<Character> getUniqueCharacters(String input) {
		return input.chars().mapToObj(i -> (char) i).collect(Collectors.toSet());
	}

	/**
	 * This method checks if the input string contains any repeated consecutive characters.
	 * It uses a regular expression to search for any character (\\w) followed by the same character (\\1).
	 * If such a pattern is found in the input string, the method returns true. Otherwise, it returns false.
	 * Example: hasDoubleCharacters("abba") returns true.
	 *
	 * @param input The string to be checked for repeated consecutive characters.
	 * @return True if the input string contains repeated consecutive characters, false otherwise.
	 */
	public static boolean hasDoubleCharacters(String input) {
		return Pattern.compile("(\\w)\\1").matcher(input).find();
	}

	/**
	 * This method replaces all occurrences of each key in the provided map with its corresponding value in the input string.
	 * It iterates over the keys of the map, and for each key, it replaces all occurrences of the key in the input string
	 * with the corresponding value from the map. The modified string is then returned.
	 * Example: replaceAll("abca", Map.of("a", "x", "b", "y")) returns "xycx".
	 *
	 * @param input           The string in which replacements are to be made.
	 * @param conversionTable The map containing the keys to be replaced and their corresponding replacement values.
	 * @return The input string after all replacements have been made.
	 */
	public static String replaceAll(String input, Map<String, String> conversionTable) {
		for (String key : conversionTable.keySet()) {
			input = input.replace(key, conversionTable.get(key));
		}
		return input;
	}

	/**
	 * This method checks if the input string starts with any of the strings in the provided list.
	 * It iterates over the list of search strings, and for each string, it checks if the input string starts with it.
	 * If it does, it returns the matching string. If no match is found after checking all search strings, it returns null.
	 * Example: startsWithAny("abacada", List.of("a", "c")) returns "a".
	 *
	 * @param input         The string to be checked.
	 * @param searchStrings The list of strings to check if the input string starts with.
	 * @return The first string from the list that the input string starts with, or null if none are found.
	 */
	public static String startsWithAny(String input, List<String> searchStrings) {
		return searchStrings.stream()
				.filter(input::startsWith)
				.findFirst()
				.orElse(null);
	}
}
