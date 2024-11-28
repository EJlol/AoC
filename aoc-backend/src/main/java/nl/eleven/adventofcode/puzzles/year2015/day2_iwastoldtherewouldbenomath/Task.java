package nl.eleven.adventofcode.puzzles.year2015.day2_iwastoldtherewouldbenomath;

import nl.eleven.adventofcode.helpers.string.PatternStringHelper;
import nl.eleven.adventofcode.helpers.string.StringSplitter;
import nl.eleven.adventofcode.helpers.utils.TriFunction;
import nl.eleven.adventofcode.models.rect.Cuboid;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("year2015day2")
public class Task implements TaskInterface<Integer> {

	private static int calculateOutput(List<String> input,
									   TriFunction<Integer, Integer, Integer, Integer> calculationMethod) {
		return input.stream().map(line -> {
			Map<String, String> parameters = PatternStringHelper.getParameters(line, "{l}x{w}x{h}");
			int l = Integer.parseInt(parameters.get("l"));
			int w = Integer.parseInt(parameters.get("w"));
			int h = Integer.parseInt(parameters.get("h"));
			return calculationMethod.apply(l, w, h);
		}).mapToInt(i -> i).sum();
	}

	@Override
	public Integer executeTask1(List<String> input) {
		return calculateOutput(input, this::getWrappingPaperArea);
	}

	@Override
	public Integer executeTask2(List<String> input) {
		return calculateOutput(input, this::getRibbonLength);
	}

	private int getRibbonLength(int l, int w, int h) {
		Cuboid gift = new Cuboid(l, w, h);
		return (gift.getWidth() * gift.getHeight() * gift.getDepth()) + gift.getSmallestPerimeter();
	}

	private int getWrappingPaperArea(int l, int w, int h) {
		Cuboid gift = new Cuboid(l, w, h);
		return gift.getSurfaceArea() + gift.getSmallestSurfaceArea();
	}
}
