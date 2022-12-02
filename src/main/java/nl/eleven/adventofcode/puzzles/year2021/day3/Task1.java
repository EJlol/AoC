package nl.eleven.adventofcode.puzzles.year2021.day3;

import nl.eleven.adventofcode.Task;
import nl.eleven.adventofcode.groupingby.CountItems;
import nl.eleven.adventofcode.table.CharacterTableMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("year2021day3task1")
public class Task1 implements Task {

	public int executeTask(List<String> input) {
		List<List<Character>> inputTable = CharacterTableMapper.map(input).rotate().contents();

		CountItems<Character> processor = new CountItems<>();
		List<Map<Character, Long>> counts = inputTable.stream().map(processor::count).toList();

		String gamma = counts.stream()
				.map(characterCounts -> {
					long zero = characterCounts.get('0');
					long one = characterCounts.get('1');
					return zero > one ? '0' : '1';
				}).map(Object::toString).collect(Collectors.joining());
		String epsilon = gamma.replace('0', '-').replace('1', '0').replace('-', '1');
		return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2);
	}
}
