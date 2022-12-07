package nl.eleven.adventofcode.puzzles.year2022.day6;

import nl.eleven.adventofcode.helpers.string.StringHelper;
import nl.eleven.adventofcode.tasks.IntegerTask;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component("year2022day6task2")
public class Task2 implements IntegerTask {

	@Override
	public int executeTask(List<String> input) {
		String inputString = input.get(0);
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
