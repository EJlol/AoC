package nl.eleven.adventofcode.puzzles.year2022.day3_rucksackreoganization;

import nl.eleven.adventofcode.helpers.list.PartitionListBy;
import nl.eleven.adventofcode.helpers.string.StringHelper;
import nl.eleven.adventofcode.helpers.string.StringSplitter;
import nl.eleven.adventofcode.models.pair.Pair;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component("year2022day3")
public class Task implements TaskInterface<Integer> {

	@Override
	public Integer executeTask1(List<String> input) {
		final String alphabet = "-abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

		return input.stream().mapToInt(line -> {
			Pair<String, String> pair = StringSplitter.partitionInTwo(line);
			Set<Character> usedLettersLeft = StringHelper.getUniqueCharacters(pair.getLeft());
			Set<Character> usedLettersRight = StringHelper.getUniqueCharacters(pair.getRight());

			Character characterTwice = usedLettersLeft.stream()
					.filter(usedLettersRight::contains)
					.findAny()
					.orElse('-');
			return alphabet.indexOf(characterTwice);
		}).sum();
	}

	@Override
	public Integer executeTask2(List<String> input) {
		final String alphabet = "-abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		return PartitionListBy.count(input, 3).stream()
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
