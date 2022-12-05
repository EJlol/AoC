package nl.eleven.adventofcode.puzzles.year2022.day3;

import nl.eleven.adventofcode.Task;
import nl.eleven.adventofcode.helpers.list.ListHelper;
import nl.eleven.adventofcode.helpers.string.StringHelper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component("year2022day3task2")
public class Task2 implements Task {

	@Override
	public int executeTask(List<String> input) {
		final String alphabet = "-abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		return ListHelper.partitionByCount(input, 3).stream()
				.mapToInt(group -> {
					Set<Character> usedS1 = StringHelper.getUniqueCharacters(group.get(0));
					Set<Character> usedS2 = StringHelper.getUniqueCharacters(group.get(1));
					Set<Character> usedS3 = StringHelper.getUniqueCharacters(group.get(2));
					Character character = usedS1.stream()
							.filter(usedS2::contains)
							.filter(usedS3::contains)
							.findAny()
							.orElse('-');
					return alphabet.indexOf(character);
				}).sum();
	}

}
