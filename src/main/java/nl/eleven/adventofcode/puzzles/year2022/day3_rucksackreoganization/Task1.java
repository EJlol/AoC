package nl.eleven.adventofcode.puzzles.year2022.day3_rucksackreoganization;

import nl.eleven.adventofcode.helpers.string.StringHelper;
import nl.eleven.adventofcode.models.pair.Pair;
import nl.eleven.adventofcode.tasks.IntegerSingleTask;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component("year2022day3task1")
public class Task1 implements IntegerSingleTask {

	@Override
	public int executeTask(List<String> input) {
		final String alphabet = "-abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

		return input.stream().mapToInt(line -> {
			Pair<String, String> pair = StringHelper.partitionInTwo(line);
			Set<Character> usedLettersLeft = StringHelper.getUniqueCharacters(pair.getLeft());
			Set<Character> usedLettersRight = StringHelper.getUniqueCharacters(pair.getRight());

			Character characterTwice = usedLettersLeft.stream()
					.filter(usedLettersRight::contains)
					.findAny()
					.orElse('-');
			return alphabet.indexOf(characterTwice);
		}).sum();
	}

}
