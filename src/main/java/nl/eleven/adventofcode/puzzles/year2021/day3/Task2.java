package nl.eleven.adventofcode.puzzles.year2021.day3;

import nl.eleven.adventofcode.Task;
import nl.eleven.adventofcode.mappers.CharacterTableMapper;
import nl.eleven.adventofcode.mappers.CountItems;
import nl.eleven.adventofcode.table.RotateTable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@Component("year2021day3task2")
public class Task2 implements Task {

	private static String findBit(List<List<Character>> table, BiPredicate<Long, Long> predicate) {
		int position = 0;
		while (table.size() > 1) {
			int finalPosition = position;

			List<List<Character>> rotatedTable = new RotateTable<Character>().map(table);
			CountItems<Character> processor = new CountItems<>();
			List<Map<Character, Long>> counts = rotatedTable.stream().map(processor::count).toList();

			Map<Character, Long> columnCount = counts.get(position);
			if (predicate.test(columnCount.get('0'), columnCount.get('1'))) {
				table = table.stream().filter(binaryCode -> binaryCode.get(finalPosition).equals('0')).toList();
			} else {
				table = table.stream().filter(binaryCode -> binaryCode.get(finalPosition).equals('1')).toList();
			}
			position++;
		}
		return table.get(0).stream().map(Object::toString).collect(Collectors.joining());
	}

	public int executeTask(List<String> input) {
		List<List<Character>> table = new CharacterTableMapper().map(input);

		String o2 = findBit(table, (zero, one) -> zero > one);
		String co2 = findBit(table, (zero, one) -> one >= zero);

		return Integer.parseInt(o2, 2) * Integer.parseInt(co2, 2);
	}
}
