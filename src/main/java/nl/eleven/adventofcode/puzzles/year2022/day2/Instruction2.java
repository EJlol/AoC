package nl.eleven.adventofcode.puzzles.year2022.day2;

import nl.eleven.adventofcode.rps.RpsShape;
import nl.eleven.adventofcode.rps.WinState;

import java.util.Map;

public class Instruction2 {

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

	public Instruction2(String instruction) {
		String[] splitInstruction = instruction.split(" ");
		left = shapeLetters.get(splitInstruction[0]);
		right = winStateLetters.get(splitInstruction[1]);
	}

	public RpsShape getLeft() {
		return left;
	}

	public WinState getRight() {
		return right;
	}
}
