package nl.eleven.adventofcode.puzzles.year2022.day9_ropebridge;

import nl.eleven.adventofcode.models.position.Position;
import nl.eleven.adventofcode.tasks.IntegerDoubleTask;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component("year2022day9")
public class Task implements IntegerDoubleTask {

	Map<Position, Position> lookupTable = Map.ofEntries(
			// West
			Map.entry(new Position(-2, -1), Position.NORTHWEST),
			Map.entry(new Position(-2, 0), Position.WEST),
			Map.entry(new Position(-2, 1), Position.SOUTHWEST),

			// East
			Map.entry(new Position(2, -1), Position.NORTHEAST),
			Map.entry(new Position(2, 0), Position.EAST),
			Map.entry(new Position(2, 1), Position.SOUTHEAST),

			// North
			Map.entry(new Position(-1, -2), Position.NORTHWEST),
			Map.entry(new Position(0, -2), Position.NORTH),
			Map.entry(new Position(1, -2), Position.NORTHEAST),

			// South
			Map.entry(new Position(-1, 2), Position.SOUTHWEST),
			Map.entry(new Position(0, 2), Position.SOUTH),
			Map.entry(new Position(1, 2), Position.SOUTHEAST),

			// Diagonals
			Map.entry(new Position(-2, -2), Position.NORTHWEST),
			Map.entry(new Position(-2, 2), Position.SOUTHWEST),
			Map.entry(new Position(2, -2), Position.NORTHEAST),
			Map.entry(new Position(2, 2), Position.SOUTHEAST)
	);

	Position snakeHead = new Position(0, 0);

	List<Position> snakeTail;

	private static List<Position> initializeSnakeTail(int tailLength) {
		List<Position> snakeTail = new ArrayList<>();
		for (int i = 0; i < tailLength; i++) {
			snakeTail.add(new Position(0, 0));
		}
		return snakeTail;
	}

	@Override
	public int executeTask1(List<String> input) {
		int snakeLength = 1;
		return simulateSnake(input, snakeLength);
	}

	@Override
	public int executeTask2(List<String> input) {
		int snakeLength = 9;
		return simulateSnake(input, snakeLength);
	}

	private Position getTailMovement(Position currentPart, Position withPreviousPart) {
		Position relativePositionOfHead = withPreviousPart.min(currentPart);
		return lookupTable.get(relativePositionOfHead);
	}

	private void moveSnake(Position direction) {
		snakeHead = snakeHead.plus(direction);
		for (int j = 0; j < snakeTail.size(); j++) {
			Position tailPart = snakeTail.get(j);
			Position headPart = j == 0 ? snakeHead : snakeTail.get(j - 1);
			snakeTail.set(j, tailPart.plus(getTailMovement(tailPart, headPart)));
		}
	}

	private int simulateSnake(List<String> input, int tailLength) {
		snakeHead = new Position(0, 0);
		snakeTail = initializeSnakeTail(tailLength);

		Set<Position> visited = new HashSet<>();
		visited.add(new Position(0, 0));

		input.forEach(line -> {
			int distance = Integer.parseInt(line.substring(2));
			for (int i = 0; i < distance; i++) {
				if (line.startsWith("U")) {
					moveSnake(Position.NORTH);
				} else if (line.startsWith("R")) {
					moveSnake(Position.EAST);
				} else if (line.startsWith("L")) {
					moveSnake(Position.WEST);
				} else if (line.startsWith("D")) {
					moveSnake(Position.SOUTH);
				}
				visited.add(snakeTail.get(tailLength - 1));
			}
		});

		return visited.size();
	}
}
