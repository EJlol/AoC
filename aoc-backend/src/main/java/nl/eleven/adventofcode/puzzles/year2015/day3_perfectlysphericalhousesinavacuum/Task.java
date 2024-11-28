package nl.eleven.adventofcode.puzzles.year2015.day3_perfectlysphericalhousesinavacuum;

import nl.eleven.adventofcode.helpers.string.StringHelper;
import nl.eleven.adventofcode.models.maptable.MapTable;
import nl.eleven.adventofcode.models.position.Position;
import nl.eleven.adventofcode.models.turtle.Turtle;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("year2015day3")
public class Task implements TaskInterface<Integer> {

	@Override
	public Integer executeTask1(List<String> input) {
		MapTable<Integer> houses = new MapTable<>();
		Turtle turtle = new Turtle(Position.ZERO);
		houses.plot(turtle.getPosition(), 1);
		StringHelper.getCharacters(input.getFirst()).forEach(c -> {
			turtle.move("" + c);
			houses.plot(turtle.getPosition(), houses.getOrDefault(turtle.getPosition(), 0) + 1);
		});
		return houses.size();
	}

	@Override
	public Integer executeTask2(List<String> input) {
		MapTable<Integer> houses = new MapTable<>();
		Turtle turtle1 = new Turtle(Position.ZERO);
		Turtle turtle2 = new Turtle(Position.ZERO);
		final boolean[] robotTurtleTurn = {false};

		houses.plot(turtle1.getPosition(), 1);
		houses.plot(turtle2.getPosition(), 1);
		StringHelper.getCharacters(input.getFirst()).forEach(c -> {
			if (robotTurtleTurn[0]) {
				turtle1.move("" + c);
				houses.plot(turtle1.getPosition(), houses.getOrDefault(turtle1.getPosition(), 0) + 1);
			} else {
				turtle2.move("" + c);
				houses.plot(turtle2.getPosition(), houses.getOrDefault(turtle2.getPosition(), 0) + 1);
			}
			robotTurtleTurn[0] = !robotTurtleTurn[0];
		});
		return houses.size();
	}
}
