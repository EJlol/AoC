package nl.eleven.adventofcode.puzzles.year2021.day3;

import nl.eleven.adventofcode.Task;
import nl.eleven.adventofcode.helpers.list.groupingby.CountItems;
import nl.eleven.adventofcode.models.table.CharacterTableMapper;
import nl.eleven.adventofcode.models.table.Table;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@Component("year2021day3task2")
public class Task2 implements Task {

	private static String findBit(Table<Character> table, BiPredicate<Long, Long> predicate) {
		int position = 0;
		while (table.getHeight() > 1) {
			int finalPosition = position;

			Table<Character> rotatedTable = table.rotate();
			CountItems<Character> processor = new CountItems<>();
			List<Map<Character, Long>> counts = rotatedTable.contents().stream().map(processor::count).toList();

			Map<Character, Long> countsMap = counts.get(position);
			Long zeroCount = countsMap.getOrDefault('0', 0L);
			Long oneCount = countsMap.getOrDefault('1', 0L);
			if (predicate.test(zeroCount, oneCount)) {
				table = table.filterRows(binaryCode -> binaryCode.get(finalPosition).equals('0'));
			} else {
				table = table.filterRows(binaryCode -> binaryCode.get(finalPosition).equals('1'));
			}
			position++;
		}
		return table.getRow(0).stream().map(Object::toString).collect(Collectors.joining());
	}

	public int executeTask(List<String> input) {
		Table<Character> table = CharacterTableMapper.map(input);

		String o2 = findBit(table, (zero, one) -> zero > one);
		String co2 = findBit(table, (zero, one) -> one >= zero);

		return Integer.parseInt(o2, 2) * Integer.parseInt(co2, 2);
	}
}
