package nl.eleven.adventofcode.puzzles.year2022.day2_rockpaperscissors;

import nl.eleven.adventofcode.models.rps.RpsShape;

import java.util.Map;

class Instruction {

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

	Instruction(String instruction) {
		String[] splitInstruction = instruction.split(" ");
		left = shapeLetters.get(splitInstruction[0]);
		right = shapeLetters.get(splitInstruction[1]);
	}

	RpsShape getLeft() {
		return left;
	}

	RpsShape getRight() {
		return right;
	}
}
