package nl.eleven.adventofcode.puzzles.year2023.day2_cubeconundrum;

import nl.eleven.adventofcode.helpers.string.StringSplitter;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("year2023day2")
public class Task implements TaskInterface<Integer> {

	@Override
	public Integer executeTask1(List<String> input) {
		return input.stream().map(s -> {
			String[] splittedGame = s.split(": ");
			return new Game(Integer.parseInt(splittedGame[0].split(" ")[1]), splittedGame[1]);
		})
				.filter(Game::isValidForTask1)
				.mapToInt(g -> g.id).sum();
	}

	@Override
	public Integer executeTask2(List<String> input) {
		return input.stream().map(s -> {
					String[] splittedGame = s.split(": ");
					return new Game(Integer.parseInt(splittedGame[0].split(" ")[1]), splittedGame[1]);
				})
				.mapToInt(Game::getPower).sum();
	}

	static class Game {
		int id;
		Map<String, Integer> colorMaxMap;

		public Game(int id, String gameInput) {
			this.id = id;
			this.colorMaxMap = new HashMap<>();

			List<String> colors = StringSplitter.splitAtAny(gameInput, List.of(", ", "; "));
			for(String c : colors) {
				int amount = Integer.parseInt(c.split(" ")[0]);
				colorMaxMap.compute(c.split(" ")[1], (_, v) -> v == null || amount > v ? amount : v);
			}
		}

		public boolean isValidForTask1() {
			return colorMaxMap.get("red") <= 12 && colorMaxMap.get("green") <= 13 && colorMaxMap.get("blue") <= 14;
		}

		public int getPower() {
			return colorMaxMap.get("red") * colorMaxMap.get("green") * colorMaxMap.get("blue");
		}
	}
}
