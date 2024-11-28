package nl.eleven.adventofcode.puzzles.year2015.day6_problablyafirehazard;

import nl.eleven.adventofcode.helpers.string.MultiPatternStringHelper;
import nl.eleven.adventofcode.models.rect.Rectangle;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Component("year2015day6")
public class Task implements TaskInterface<Long> {

	private final int[][] lights = new int[1000][1000];

	private static Rectangle getRectangle(Map<String, String> parameters) {
		return new Rectangle(
				Integer.parseInt(parameters.get("x")),
				Integer.parseInt(parameters.get("y")),
				Integer.parseInt(parameters.get("x2")) - Integer.parseInt(parameters.get("x")),
				Integer.parseInt(parameters.get("y2")) - Integer.parseInt(parameters.get("y")));
	}

	@Override
	public Long executeTask1(List<String> input) {
		MultiPatternStringHelper<Commands> multiPatternStringHelper = getMultiPatternStringHelper();
		input.forEach(instruction -> {
			MultiPatternStringHelper.PatternResult<Commands> result = multiPatternStringHelper.getParameters(instruction);
			changeLight(result.key().getActionTask1(), getRectangle(result.parameters()));
		});

		return IntStream.range(0, 1000)
				.flatMap(i -> IntStream.range(0, 1000)
						.filter(j -> lights[i][j] == 1))
				.count();
	}

	@Override
	public Long executeTask2(List<String> input) {
		MultiPatternStringHelper<Commands> multiPatternStringHelper = getMultiPatternStringHelper();
		input.forEach(instruction -> {
			MultiPatternStringHelper.PatternResult<Commands> result = multiPatternStringHelper.getParameters(instruction);
			changeLight(result.key().getActionTask2(), getRectangle(result.parameters()));
		});

		return LongStream.range(0, 1000)
				.flatMap(i ->
						LongStream.range(0, 1000).map(j -> lights[(int) i][(int) j])).sum();
	}

	private static MultiPatternStringHelper<Commands> getMultiPatternStringHelper() {
		MultiPatternStringHelper<Commands> multiPatternStringHelper = new MultiPatternStringHelper<>();
		multiPatternStringHelper.addPattern(Commands.TURN_ON, "turn on {x},{y} through {x2},{y2}");
		multiPatternStringHelper.addPattern(Commands.TURN_OFF, "turn off {x},{y} through {x2},{y2}");
		multiPatternStringHelper.addPattern(Commands.TOGGLE, "toggle {x},{y} through {x2},{y2}");
		return multiPatternStringHelper;
	}

	private void changeLight(Function<Integer, Integer> action, Rectangle rectangle) {
		for (int i = rectangle.getX(); i <= rectangle.getX() + rectangle.getWidth(); i++) {
			for (int j = rectangle.getY(); j <= rectangle.getY() + rectangle.getHeight(); j++) {
				lights[i][j] = action.apply(lights[i][j]);
			}
		}
	}

}
