package nl.eleven.adventofcode.puzzles.year2023.day2_cubeconundrum;

import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.List;

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

}
