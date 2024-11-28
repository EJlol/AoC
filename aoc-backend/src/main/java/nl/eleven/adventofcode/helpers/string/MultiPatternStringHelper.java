package nl.eleven.adventofcode.helpers.string;

import java.util.HashMap;
import java.util.Map;

public class MultiPatternStringHelper<T> {
	Map<T, String> patterns = new HashMap<>();

	public void addPattern(T key, String pattern) {
		patterns.put(key, pattern);
	}

	/**
	 * This method is used to get the parameters from a given input string based on the patterns stored in the class.
	 * It uses a PatternStringHelper.ParameterStringSplitter to parse the input string with each pattern.
	 * The pattern that matches the longest part of the input string is selected, and its parameters are returned.
	 *
	 * @param input The input string to parse.
	 * @return A PatternResult object containing the key of the pattern that matched the longest part of the input string
	 *         and a map of the parameters that were found. If no pattern matches the input string, null is returned.
	 */
	public PatternResult<T> getParameters(String input) {
		PatternStringHelper.ParameterStringSplitter splitter = new PatternStringHelper.ParameterStringSplitter();
		PatternResult<T> result = null;

		int maxPatternLength = 0;
		for (T pattern : patterns.keySet()) {
			try {
				// Try to parse the input string with the current pattern.
				splitter.parse(input, patterns.get(pattern));
				// If the length of the pattern is greater than the maximum found so far,
				// update the maximum length and store the result.
				if (splitter.getPatternLength() > maxPatternLength) {
					maxPatternLength = splitter.getPatternLength();
					result = new PatternResult<>(pattern, splitter.getResult());
				}
			} catch (IllegalArgumentException _) {
				// If the pattern does not match the input string, ignore the exception and continue with the next pattern.
			}

		}

		// Return the result with the longest matching pattern, or null if no pattern matched the input string.
		return result;
	}

	public record PatternResult<T>(T key, Map<String, String> parameters) {
	}
}
