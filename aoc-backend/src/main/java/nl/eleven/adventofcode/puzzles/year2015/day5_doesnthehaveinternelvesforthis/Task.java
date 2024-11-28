package nl.eleven.adventofcode.puzzles.year2015.day5_doesnthehaveinternelvesforthis;

import nl.eleven.adventofcode.helpers.string.StringHelper;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Component("year2015day5")
public class Task implements TaskInterface<Long> {

	@Override
	public Long executeTask1(List<String> input) {
		return input.stream().filter(this::isNice1).count();
	}

	@Override
	public Long executeTask2(List<String> input) {
		return input.stream().filter(this::isNice2).count();
	}

	private boolean isNice1(String line) {
		Map<Character, Long> frequencies = StringHelper.getCharacterFrequencies(line);
		long vowels = frequencies.getOrDefault('a', 0L) +
				frequencies.getOrDefault('e', 0L) +
				frequencies.getOrDefault('i', 0L) +
				frequencies.getOrDefault('o', 0L) +
				frequencies.getOrDefault('u', 0L);
		boolean hasDoubleCharacters = StringHelper.hasDoubleCharacters(line);
		boolean hasForbiddenStrings = line.contains("ab") || line.contains("cd") || line.contains("pq") || line.contains("xy");

		return vowels >= 3 && hasDoubleCharacters && !hasForbiddenStrings;
	}

	private boolean isNice2(String line) {
		Pattern patternDoublePair = Pattern.compile("(\\w\\w)\\w*\\1");
		boolean hasDoublePair = patternDoublePair.matcher(line).find();

		Pattern patternXYX = Pattern.compile("(\\w)\\w\\1");
		boolean hasXYX = patternXYX.matcher(line).find();
		return hasDoublePair && hasXYX;
	}
}
