package nl.eleven.adventofcode.puzzles.year2021.day3;

import nl.eleven.adventofcode.Task;
import nl.eleven.adventofcode.processors.CountItemsVertical;
import nl.eleven.adventofcode.readers.CharacterReader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component("year2021day3task1")
public class Task1 implements Task {

	private static final String URL = "https://adventofcode.com/2021/day/3/input";

	public int executeTask() {
		Stream<List<Character>> stream = new CharacterReader().read(URL);

		CountItemsVertical<Character> processor = new CountItemsVertical<>();
		List<Map<Character, Integer>> counts = processor.process(stream);

		String gamma = counts.stream()
				.map(characterCounts -> {
					int zero = characterCounts.get('0');
					int one = characterCounts.get('1');
					return zero > one ? '0' : '1';
				}).map(Object::toString).collect(Collectors.joining());
		String epsilon = gamma.replace('0', '-').replace('1', '0').replace('-', '1');
		return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2);
	}
}
