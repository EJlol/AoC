package nl.eleven.adventofcode.puzzles.year2022.day2_rockpaperscissors;

import nl.eleven.adventofcode.helpers.inputmappers.InstructionMapper;
import nl.eleven.adventofcode.models.rps.RpsMatch;
import nl.eleven.adventofcode.models.rps.RpsShape;
import nl.eleven.adventofcode.models.rps.WinState;
import nl.eleven.adventofcode.tasks.IntegerSingleTask;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component("year2022day2task2")
public class Task2 implements IntegerSingleTask {

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
		List<Instruction2> instructions = InstructionMapper.map(input, Instruction2.class);
		return instructions.stream().mapToInt(instruction -> {
			RpsMatch match = new RpsMatch(instruction.getLeft(), instruction.getRight());
			int shapeScore = scoresShape.get(match.getYou());
			int winScore = scoresWinState.get(match.getResult());
			return shapeScore + winScore;
		}).sum();
	}

}
