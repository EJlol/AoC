package nl.eleven.adventofcode.puzzles.year2021.day3;

import nl.eleven.adventofcode.helpers.list.ListHelper;
import nl.eleven.adventofcode.models.table.CharacterTableMapper;
import nl.eleven.adventofcode.models.table.Table;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@Component("year2021day3")
public class Task implements TaskInterface<Integer> {

	/**
	 * This method is used to find a specific bit in a binary table based on a given predicate.
	 * The method rotates the table and counts the occurrences of '0' and '1' in each position.
	 * It then uses the provided predicate to determine which character ('0' or '1') to filter by.
	 * The table is then filtered to only include rows where the character at the current position matches the filter character.
	 * This process is repeated until the height of the table is 1.
	 * The method finally returns the remaining row in the table as a string.
	 *
	 * @param table     A Table<Character> representing the binary table.
	 * @param predicate A BiPredicate<Long, Long> used to determine which character to filter by. The predicate takes two arguments: the count of '0' and the count of '1'.
	 * @return A String representing the remaining row in the table.
	 */
	private static String findBit(Table<Character> table, BiPredicate<Long, Long> predicate) {
		int position = 0;
		while (table.getHeight() > 1) {
			Table<Character> rotatedTable = table.rotate();
			List<Map<Character, Long>> counts = rotatedTable.contents().stream().map(ListHelper::countEqualItems).toList();

			Map<Character, Long> countsMap = counts.get(position);
			Long zeroCount = countsMap.getOrDefault('0', 0L);
			Long oneCount = countsMap.getOrDefault('1', 0L);

			final int finalPosition = position;
			final char filterChar = predicate.test(zeroCount, oneCount) ? '0' : '1';
			table = table.filterRows(binaryCode -> binaryCode.get(finalPosition).equals(filterChar));
			position++;
		}
		return table.getRow(0).stream().map(Object::toString).collect(Collectors.joining());
	}

	public Integer executeTask1(List<String> input) {
		String gamma = CharacterTableMapper
				.map(input)
				.rotate()
				.contents()
				.stream()
				.map(ListHelper::countEqualItems)
				.map(characterFrequencies -> {
					long zero = characterFrequencies.get('0');
					long one = characterFrequencies.get('1');
					return zero > one ? '0' : '1';
				})
				.map(Object::toString)
				.collect(Collectors.joining());

		String epsilon = gamma.replace('0', '-').replace('1', '0').replace('-', '1');
		return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2);
	}

	public Integer executeTask2(List<String> input) {
		Table<Character> table = CharacterTableMapper.map(input);

		String o2 = findBit(table, (zero, one) -> zero > one);
		String co2 = findBit(table, (zero, one) -> one >= zero);

		return Integer.parseInt(o2, 2) * Integer.parseInt(co2, 2);
	}
}
