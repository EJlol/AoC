package nl.eleven.adventofcode.puzzles.year2022.day2_rockpaperscissors;

import nl.eleven.adventofcode.models.rps.RpsShape;

import java.util.Map;

public class Instruction {

	RpsShape left;

	RpsShape right;

	Map<String, RpsShape> shapeLetters = Map.of(
			"A", RpsShape.ROCK,
			"B", RpsShape.PAPER,
			"C", RpsShape.SCISSORS,
			"X", RpsShape.ROCK,
			"Y", RpsShape.PAPER,
			"Z", RpsShape.SCISSORS
	);

	public Instruction(String instruction) {
		String[] splitInstruction = instruction.split(" ");
		left = shapeLetters.get(splitInstruction[0]);
		right = shapeLetters.get(splitInstruction[1]);
	}

	public RpsShape getLeft() {
		return left;
	}

	public RpsShape getRight() {
		return right;
	}
}
