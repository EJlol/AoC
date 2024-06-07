package nl.eleven.adventofcode.puzzles.year2022.day2_rockpaperscissors;

import nl.eleven.adventofcode.models.rps.RpsMatch;
import nl.eleven.adventofcode.models.rps.RpsShape;
import nl.eleven.adventofcode.models.rps.WinState;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component("year2022day2")
public class Task implements TaskInterface<Integer> {

	EnumMap<RpsShape, Integer> scoresShape = new EnumMap<>(Map.of(
			RpsShape.ROCK, 1,
			RpsShape.PAPER, 2,
			RpsShape.SCISSORS, 3
	));

	EnumMap<WinState, Integer> scoresWinState = new EnumMap<>(Map.of(
			WinState.LOSE, 0,
			WinState.DRAW, 3,
			WinState.WIN, 6
	));

	@Override
	public Integer executeTask1(List<String> input) {
		List<Instruction> instructions = input.stream()
				.map(Instruction::new)
				.toList();

		return instructions.stream().mapToInt(instruction -> {
			RpsShape opponentShape = instruction.getLeft();
			RpsShape yourShape = instruction.getRight();

			RpsMatch match = new RpsMatch(yourShape, opponentShape);
			int shapeScore = scoresShape.get(yourShape);
			int winScore = scoresWinState.get(match.getResult());
			return shapeScore + winScore;
		}).sum();
	}

	@Override
	public Integer executeTask2(List<String> input) {
		List<Instruction> instructions = input.stream()
				.map(Instruction::new)
				.toList();

		return instructions.stream().mapToInt(instruction -> {
			RpsMatch match = new RpsMatch(instruction.getLeft(), instruction.getRight());
			int shapeScore = scoresShape.get(match.getYou());
			int winScore = scoresWinState.get(match.getResult());
			return shapeScore + winScore;
		}).sum();
	}

}
