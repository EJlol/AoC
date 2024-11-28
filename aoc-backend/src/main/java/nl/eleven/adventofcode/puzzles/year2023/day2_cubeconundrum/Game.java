package nl.eleven.adventofcode.puzzles.year2023.day2_cubeconundrum;

import nl.eleven.adventofcode.helpers.string.StringSplitter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Game {

	Map<String, Integer> colorMaxMap;

	int id;

	public Game(int id, String gameInput) {
		this.id = id;
		this.colorMaxMap = new HashMap<>();

		List<String> colors = StringSplitter.splitAtAny(gameInput, List.of(", ", "; "));
		for (String c : colors) {
			int amount = Integer.parseInt(c.split(" ")[0]);
			colorMaxMap.compute(c.split(" ")[1], (_, v) -> v == null || amount > v ? amount : v);
		}
	}

	int getPower() {
		return colorMaxMap.get("red") * colorMaxMap.get("green") * colorMaxMap.get("blue");
	}

	boolean isValidForTask1() {
		return colorMaxMap.get("red") <= 12 && colorMaxMap.get("green") <= 13 && colorMaxMap.get("blue") <= 14;
	}
}
