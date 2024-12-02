package nl.eleven.adventofcode.helpers.pattern;

import java.util.HashMap;
import java.util.Map;

public class PatternStringHelper {

	public static Map<String, String> getParameters(String input, String pattern) {
		ParameterStringSplitter splitter = new ParameterStringSplitter();
		splitter.parse(input, pattern);
		return splitter.getStringResults();
	}

	protected static class ParameterStringSplitter {

		String input;

		Map<String, Integer> integerResults;

		String pattern;

		int patternLength;

		Map<String, String> stringResults;

		public ParameterStringSplitter() {
			stringResults = new HashMap<>();
			integerResults = new HashMap<>();
		}

		public Map<String, Integer> getIntegerResults() {
			return integerResults;
		}

		public int getPatternLength() {
			return patternLength;
		}

		public Map<String, String> getStringResults() {
			return stringResults;
		}

		public void parse(String input, String pattern) {
			this.input = input;
			this.pattern = pattern;
			this.stringResults = new HashMap<>();
			this.integerResults = new HashMap<>();
			this.patternLength = 0;

			readAllValues();
		}

		private int findLastCharacterIndexOfValue() {
			int nextCurlyBracketIndex = findNextCurlyBracketIndex();
			String searchString = pattern.substring(pattern.indexOf('}') + 1, nextCurlyBracketIndex);
			int searchIndex = input.indexOf(searchString);
			if (searchIndex == -1) {
				throw new IllegalArgumentException("Mismatch in pattern.\nPattern = " + pattern + "\nInput = " + input);
			}
			return searchIndex;
		}

		private int findNextCurlyBracketIndex() {
			int nextCurlyBracketIndex = pattern.indexOf('{', 1);
			if (nextCurlyBracketIndex == -1) {
				// Last token
				nextCurlyBracketIndex = pattern.length();
			}
			return nextCurlyBracketIndex;
		}

		private String findNextKey() {
			skipSameCharacters();
			if (isFinished()) {
				return null;
			}

			validatePatternStartTag();

			return pattern.substring(1, pattern.indexOf('}'));
		}

		private boolean isFinished() {
			return pattern.isEmpty() || input.isEmpty();
		}

		private void readAllValues() {
			while (!input.isEmpty()) {
				String key = findNextKey();
				if (key == null) break;

				readValueAndStore(key);
			}
		}

		private void readValueAndStore(String key) {
			int searchIndex = findLastCharacterIndexOfValue();
			String value = input.substring(0, searchIndex);

			if (searchIndex == 0) {
				value = input;
				searchIndex = input.length();
			}

			if (key.endsWith(":i")) {
				integerResults.put(key.substring(0, key.length() - 2), Integer.parseInt(value.trim()));
			} else {
				stringResults.put(key, value.trim());
			}

			input = input.substring(searchIndex);
			pattern = pattern.substring(pattern.indexOf('}') + 1);
		}

		private void skipSameCharacters() {
			while (!pattern.isEmpty() && !input.isEmpty() && pattern.charAt(0) == input.charAt(0)) {
				input = input.substring(1);
				pattern = pattern.substring(1);
				patternLength++;
			}
		}

		private void validatePatternStartTag() {
			if (pattern.charAt(0) != '{') {
				throw new IllegalArgumentException("Mismatch in pattern.\nPattern = " + pattern + "\nInput = " + input);
			}
		}
	}
}
