package nl.eleven.adventofcode.puzzles.year2023.day1_trebuchet;

import nl.eleven.adventofcode.helpers.string.StringHelper;
import nl.eleven.adventofcode.helpers.utils.Constants;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("year2023day1")
public class Task implements TaskInterface<Integer> {

	private static String getNumberFromInput(String input) {
		List<String> digitWords = Constants.DIGITS.keySet().stream().toList();
		String firstDigit = StringHelper.findLeftMostString(input, s -> Character.isDigit(s.charAt(0)) || StringHelper.startsWithAny(s, digitWords) != null);
		String secondDigit = StringHelper.findRightMostString(input, s -> Character.isDigit(s.charAt(0)) || StringHelper.startsWithAny(s, digitWords) != null);
		return firstDigit + secondDigit;
	}

	@Override
	public Integer executeTask1(List<String> input) {
		return input.stream()
				.map(s -> "" + StringHelper.findLeftMostCharacter(s, Character::isDigit) + StringHelper.findRightMostCharacter(s, Character::isDigit))
				.mapToInt(Integer::parseInt)
				.sum();
	}

	@Override
	public Integer executeTask2(List<String> input) {
		return input.stream()
				.map(Task::getNumberFromInput)
				.map(s -> StringHelper.replaceAll(s, Constants.STRING_DIGITS))
				.map(s -> s.substring(0, 2))
				.mapToInt(Integer::parseInt)
				.sum();
	}
}
