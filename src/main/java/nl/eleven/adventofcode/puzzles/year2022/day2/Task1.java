package nl.eleven.adventofcode.puzzles.year2022.day2;

import nl.eleven.adventofcode.Task;
import nl.eleven.adventofcode.helpers.inputmappers.InstructionMapper;
import nl.eleven.adventofcode.models.rps.RpsMatch;
import nl.eleven.adventofcode.models.rps.RpsShape;
import nl.eleven.adventofcode.models.rps.WinState;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component("year2022day2task1")
public class Task1 implements Task {

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
	public int executeTask(List<String> input) {
		List<Instruction> instructions = InstructionMapper.map(input, Instruction.class);
		return instructions.stream().mapToInt(instruction -> {
			RpsShape opponentShape = instruction.getLeft();
			RpsShape yourShape = instruction.getRight();

			RpsMatch match = new RpsMatch(yourShape, opponentShape);
			int shapeScore = scoresShape.get(yourShape);
			int winScore = scoresWinState.get(match.getResult());
			return shapeScore + winScore;
		}).sum();
	}

}