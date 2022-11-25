package nl.eleven.adventofcode.puzzles.year2021.day3;

import nl.eleven.adventofcode.Task;
import nl.eleven.adventofcode.processors.CountItemsVertical;
import nl.eleven.adventofcode.readers.CharacterTableReader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component("year2021day3task2")
public class Task2 implements Task {

	private static final CountItemsVertical<Character> processor = new CountItemsVertical<>();

	private static String findBit(List<List<Character>> table, BiPredicate<Integer, Integer> predicate) {
		int position = 0;
		while (table.size() > 1) {
			int finalPosition = position;
			List<Map<Character, Integer>> counts = processor.process(table.stream());
			Map<Character, Integer> columnCount = counts.get(position);
			if (predicate.test(columnCount.get('0'), columnCount.get('1'))) {
				table = table.stream().filter(binaryCode -> binaryCode.get(finalPosition).equals('0')).toList();
			} else {
				table = table.stream().filter(binaryCode -> binaryCode.get(finalPosition).equals('1')).toList();
			}
			position++;
		}
		return table.get(0).stream().map(Object::toString).collect(Collectors.joining());
	}

	public int executeTask(Stream<String> inputStream) {
		List<List<Character>> table = new CharacterTableReader().parse(inputStream).toList();

		String o2 = findBit(table, (zero, one) -> zero > one);
		String co2 = findBit(table, (zero, one) -> one >= zero);

		return Integer.parseInt(o2, 2) * Integer.parseInt(co2, 2);
	}
}
