package nl.eleven.adventofcode.puzzles.year2022.day6;

import nl.eleven.adventofcode.tasks.IntegerTask;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("year2022day6task1")
public class Task1 implements IntegerTask {

	@Override
	public int executeTask(List<String> input) {
		Pattern pattern = Pattern.compile("(\\w)(?:(?!\\1.))(\\w)(?:(?!\\1|\\2.))(\\w)(?:(?!\\1|\\2|\\3.))(\\w)");
		Matcher match = pattern.matcher(input.get(0));
		if (match.find()) {
			return match.start() + 4;
		}
		return 0;
	}

}
