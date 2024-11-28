package nl.eleven.adventofcode.puzzles.year2022.day2_rockpaperscissors;

import nl.eleven.adventofcode.models.rps.RpsShape;
import nl.eleven.adventofcode.models.rps.WinState;

import java.util.Map;

class Instruction2 {

	RpsShape left;

	WinState right;

	Map<String, RpsShape> shapeLetters = Map.of(
			"A", RpsShape.ROCK,
			"B", RpsShape.PAPER,
			"C", RpsShape.SCISSORS
	);

	Map<String, WinState> winStateLetters = Map.of(
			"X", WinState.LOSE,
			"Y", WinState.DRAW,
			"Z", WinState.WIN
	);

	Instruction2(String instruction) {
		String[] splitInstruction = instruction.split(" ");
		left = shapeLetters.get(splitInstruction[0]);
		right = winStateLetters.get(splitInstruction[1]);
	}

	RpsShape getLeft() {
		return left;
	}

	WinState getRight() {
		return right;
	}
}
