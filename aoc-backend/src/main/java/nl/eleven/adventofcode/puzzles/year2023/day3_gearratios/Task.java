package nl.eleven.adventofcode.puzzles.year2023.day3_gearratios;

import nl.eleven.adventofcode.models.position.Position;
import nl.eleven.adventofcode.models.rect.ColumnSpan;
import nl.eleven.adventofcode.models.rect.Rectangle;
import nl.eleven.adventofcode.models.table.CharacterTableMapper;
import nl.eleven.adventofcode.models.table.Table;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("year2023day3")
public class Task implements TaskInterface<Long> {

	private static List<Character> getSurroundingCharacters(ColumnSpan span, Table<Character> table) {
		Rectangle rectangle = span.getRectangle().growAllDirections();
		return table.getValuesFromRectangle(rectangle);
	}

	/**
	 * This method is used to execute the first task of the day.
	 * It maps the input into a table of characters and then finds all rectangles in the table that contain digits.
	 * For each rectangle, it checks the characters to the north and south of the rectangle.
	 * If any of these characters are not digits and not '.', the rectangle is included in the result.
	 * The method then converts each remaining rectangle into a number by joining the characters in the corresponding row of the table into a string and parsing it as an integer.
	 * The method finally returns the sum of these numbers.
	 *
	 * @param input A List<String> representing the input for the task.
	 * @return An Integer representing the sum of the numbers obtained from the rectangles.
	 */
	@Override
	public Long executeTask1(List<String> input) {
		Table<Character> table = CharacterTableMapper.map(input);
		return table.getColumnSpansByPredicate(Character::isDigit).stream()
				.filter(columnSpan -> {
					List<Character> characters = getSurroundingCharacters(columnSpan, table);
					return characters.stream().anyMatch(ch -> !Character.isDigit(ch) && ch != '.');
				})
				.mapToLong(columnSpan -> getValueFromColumnSpan(columnSpan, table))
				.sum();
	}

	@Override
	public Long executeTask2(List<String> input) {
		Table<Character> table = CharacterTableMapper.map(input);
		List<ColumnSpan> columnSpans = table.getColumnSpansByPredicate(Character::isDigit);

		List<Position> gearPositions = table.getAllPositionsByValue('*');
		return gearPositions.stream().mapToLong(gearPosition -> {
			List<ColumnSpan> neighbourSpans = columnSpans.stream().filter(columnSpan -> columnSpan.isNeighbourPosition(gearPosition)).toList();
			if (neighbourSpans.size() == 2) {
				ColumnSpan leftSpan = neighbourSpans.get(0);
				ColumnSpan rightSpan = neighbourSpans.get(1);
				return getValueFromColumnSpan(leftSpan, table) * getValueFromColumnSpan(rightSpan, table);
			}

			return 0;
		}).sum();
	}

	private long getValueFromColumnSpan(ColumnSpan columnSpan, Table<Character> table) {
		return Long.parseLong(table.getRow(columnSpan.getRow()).stream()
				.map(Object::toString)
				.collect(Collectors.joining())
				.substring(columnSpan.getX(), columnSpan.getRightX()));
	}

}
