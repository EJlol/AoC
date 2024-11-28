package nl.eleven.adventofcode.puzzles.year2022.day14_regolithreservoir;

import nl.eleven.adventofcode.helpers.string.StringSplitter;
import nl.eleven.adventofcode.models.maptable.MapTable;
import nl.eleven.adventofcode.models.position.Position;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component("year2022day14")
public class Task implements TaskInterface<Integer> {

	private MapTable<Materials> map = null;

	private static MapTable<Materials> initializeMap(List<String> input) {
		MapTable<Materials> map = new MapTable<>();
		input.forEach(lineInput -> {
			List<String> positionInputs = StringSplitter.splitAtString(lineInput, " -> ");
			List<Position> positions = positionInputs.stream().map(Position::fromString).toList();
			Position lastPosition = positions.getFirst();
			for (int i = 1; i < positions.size(); i++) {
				map.drawLine(lastPosition.x(), lastPosition.y(), positions.get(i).x(), positions.get(i)
						.y(), Materials.ROCK);
				lastPosition = positions.get(i);
			}
		});
		map.plot(500, 0, Materials.SOURCE);
		return map;
	}

	@Override
	public Integer executeTask1(List<String> input) {
		map = initializeMap(input);
		int sand = 0;
		while (spawnSand(this::mayMove1, this::mayFinish1)) {
			sand++;
		}
		map.debug();
		return sand;
	}

	@Override
	public Integer executeTask2(List<String> input) {
		map = initializeMap(input);
		map.setFloorHeight(map.getHeight() + 2, Materials.ROCK);

		int sand = 0;
		while (spawnSand(this::mayMove2, this::mayFinish2)) {
			sand++;
		}
		map.debug();
		return sand + 1;
	}

	private boolean isPositionBelowEmpty(Position sandPosition) {
		return map.isEmpty(sandPosition.x() - 1, sandPosition.y() + 1) ||
				map.isEmpty(sandPosition.x(), sandPosition.y() + 1) ||
				map.isEmpty(sandPosition.x() + 1, sandPosition.y() + 1);
	}

	private boolean mayFinish1(Position sandPosition) {
		return sandPosition.y() >= map.getHeight();
	}

	private boolean mayFinish2(Position sandPosition) {
		return sandPosition.y() == 0;
	}

	private boolean mayMove1(Position sandPosition) {
		return isPositionBelowEmpty(sandPosition) && sandPosition.y() < map.getHeight();
	}

	private boolean mayMove2(Position sandPosition) {
		return isPositionBelowEmpty(sandPosition);
	}

	private boolean spawnSand(Predicate<Position> mayMove, Predicate<Position> mayFinish) {
		Position sandPosition = new Position(500, 0);
		boolean firstTest = true;
		while (firstTest || mayMove.test(sandPosition)) {
			firstTest = false;
			if (map.isEmpty(sandPosition.x(), sandPosition.y() + 1)) {
				sandPosition = sandPosition.plus(new Position(0, 1));
			} else if (map.isEmpty(sandPosition.x() - 1, sandPosition.y() + 1)) {
				sandPosition = sandPosition.plus(new Position(-1, 1));
			} else if (map.isEmpty(sandPosition.x() + 1, sandPosition.y() + 1)) {
				sandPosition = sandPosition.plus(new Position(1, 1));
			}
		}
		if (!mayFinish.test(sandPosition)) {
			map.plot(sandPosition, Materials.SAND);
			return true;
		}

		return false;
	}

}
