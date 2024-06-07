package nl.eleven.adventofcode.puzzles.year2015.day2_iwastoldtherewouldbenomath;

import nl.eleven.adventofcode.helpers.string.StringSplitter;
import nl.eleven.adventofcode.helpers.utils.TriFunction;
import nl.eleven.adventofcode.models.rect.Cuboid;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("year2015day2")
public class Task implements TaskInterface<Integer> {

	private static int calculateOutput(List<String> input,
									   TriFunction<Integer, Integer, Integer, Integer> calculationMethod) {
		return input.stream().map(line -> {
			List<Integer> dimensions = StringSplitter.splitAtCharacter(line, 'x')
					.stream()
					.map(Integer::parseInt)
					.toList();
			int l = dimensions.get(0);
			int w = dimensions.get(1);
			int h = dimensions.get(2);
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
