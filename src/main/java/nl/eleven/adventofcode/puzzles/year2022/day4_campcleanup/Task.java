package nl.eleven.adventofcode.puzzles.year2022.day4_campcleanup;

import com.google.common.collect.Range;
import nl.eleven.adventofcode.helpers.string.StringSplitter;
import nl.eleven.adventofcode.models.pair.SimplePair;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("year2022day4")
public class Task implements TaskInterface<Integer> {

	@Override
	public Integer executeTask1(List<String> input) {
		return input.stream()
				.mapToInt(line -> {
					SimplePair<Range<Integer>> pair = StringSplitter.splitAtCharacterInTwo(line, ',').map(value -> {
						SimplePair<Integer> rangePair = StringSplitter.splitAtCharacterInTwo(value, '-')
								.map(Integer::parseInt);
						return Range.openClosed(rangePair.getLeft(), rangePair.getRight());
					});
					return pair.getLeft().encloses(pair.getRight()) || pair.getRight().encloses(pair.getLeft()) ? 1 : 0;
				})
				.sum();
	}

	@Override
	public Integer executeTask2(List<String> input) {
		return input.stream()
				.mapToInt(line -> {
					SimplePair<Range<Integer>> pair = StringSplitter.splitAtCharacterInTwo(line, ',').map(value -> {
						SimplePair<Integer> rangePair = StringSplitter.splitAtCharacterInTwo(value, '-')
								.map(Integer::parseInt);
						return Range.openClosed(rangePair.getLeft(), rangePair.getRight());
					});
					return pair.getLeft().isConnected(pair.getRight()) || pair.getRight()
							.isConnected(pair.getLeft()) ? 1 : 0;
				})
				.sum();
	}
}
