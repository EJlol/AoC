package nl.eleven.adventofcode.puzzles.year2022.day6_tuningtrouble;

import nl.eleven.adventofcode.helpers.string.StringHelper;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("year2022day6")
public class Task implements TaskInterface<Integer> {

	@Override
	public Integer executeTask1(List<String> input) {
		Pattern pattern = Pattern.compile("(\\w)(?!\\1.)(\\w)(?!\\1|\\2.)(\\w)(?!\\1|\\2|\\3.)(\\w)");
		Matcher match = pattern.matcher(input.getFirst());
		if (match.find()) {
			return match.start() + 4;
		}
		return 0;
	}

	@Override
	public Integer executeTask2(List<String> input) {
		String inputString = input.getFirst();
		for (int i = 0; i < inputString.length(); i++) {
			String substring = inputString.substring(i, i + 14);
			Set<Character> uniqueCharacters = StringHelper.getUniqueCharacters(substring);
			if (uniqueCharacters.size() == substring.length()) {
				return i + 14;
			}
		}
		return 0;
	}
}
