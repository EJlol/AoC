package nl.eleven.adventofcode.helpers.string;

import java.util.HashMap;
import java.util.Map;

public class ParameterStringHelper {

	public static Map<String, String> getParameters(String input, String pattern) {
		ParameterStringSplitter splitter = new ParameterStringSplitter();
		splitter.parse(input, pattern);
		return splitter.getResult();
	}

	protected static class ParameterStringSplitter {

		String input;

		String pattern;

		Map<String, String> result;

		public ParameterStringSplitter() {
			result = new HashMap<>();
		}

		public Map<String, String> getResult() {
			return result;
		}

		public void parse(String input, String pattern) {
			this.input = input;
			this.pattern = pattern;
			this.result = new HashMap<>();

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
			result.put(key, value);

			input = input.substring(searchIndex);
			pattern = pattern.substring(pattern.indexOf('}') + 1);
		}

		private void skipSameCharacters() {
			while (!pattern.isEmpty() && !input.isEmpty() && pattern.charAt(0) == input.charAt(0)) {
				input = input.substring(1);
				pattern = pattern.substring(1);
			}
		}

		private void validatePatternStartTag() {
			if (pattern.charAt(0) != '{') {
				throw new IllegalArgumentException("Mismatch in pattern.\nPattern = " + pattern + "\nInput = " + input);
			}
		}
	}
}
