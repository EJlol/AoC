package nl.eleven.adventofcode.puzzles.year2023.day3_gearratios;

import nl.eleven.adventofcode.models.position.Position;
import nl.eleven.adventofcode.models.rect.Rectangle;
import nl.eleven.adventofcode.models.table.CharacterTableMapper;
import nl.eleven.adventofcode.models.table.Table;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component("year2023day3")
public class Task implements TaskInterface<Integer> {

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
	public Integer executeTask1(List<String> input) {
		Table<Character> table = CharacterTableMapper.map(input);
		return table.getRectangles(Character::isDigit).stream()
				.filter(rectangle -> {
					List<Character> northSouthChars = IntStream.rangeClosed(
									rectangle.getX() - 1,
									rectangle.getX() + rectangle.getWidth() + 1)
							.mapToObj(x -> List.of(
									table.getOrDefault(x, rectangle.getY() - 1, '.'),
									table.getOrDefault(x, rectangle.getY() + 1, '.')))
							.flatMap(List::stream)
							.toList();

					return northSouthChars.stream().anyMatch(ch -> !Character.isDigit(ch) && ch != '.');
				})
				.mapToInt(rectangle -> Integer.parseInt(table.getRow(rectangle.getY()).stream()
						.map(Object::toString)
						.collect(Collectors.joining())
						.substring(rectangle.getX(), rectangle.getX() + rectangle.getWidth())))
				.sum();
	}

	@Override
	public Integer executeTask2(List<String> input) {
		Table<Character> table = CharacterTableMapper.map(input);
		List<Rectangle> rectangles = table.getRectangles(Character::isDigit);

		List<Number> numbers = rectangles.stream().filter(rectangle -> {
					int y = rectangle.getY();
					for (int x = rectangle.getX() - 1; x < rectangle.getX() + rectangle.getWidth() + 1; x++) {
						Character north = table.getOrDefault(x, y - 1, '.');
						Character south = table.getOrDefault(x, y + 1, '.');
						return (north == '*') || (south == '*');
					}
					return table.getOrDefault(rectangle.getX() - 1, rectangle.getY(), '.') == '*' ||
							table.getOrDefault(rectangle.getX() + rectangle.getWidth(), rectangle.getY(), '.') == '*';
				})
				.map(rectangle -> new Number(Integer.parseInt(table.getRow(rectangle.getY()).stream()
						.map(Object::toString)
						.collect(Collectors.joining())
						.substring(rectangle.getX(), rectangle.getX() + rectangle.getWidth())), rectangle)).toList();

		List<Rectangle> positions = table.getAllPositionsByValue('*').stream().map(Position::toRectangle).map(Rectangle::growAllDirections).toList();
		return 0;
	}

}
