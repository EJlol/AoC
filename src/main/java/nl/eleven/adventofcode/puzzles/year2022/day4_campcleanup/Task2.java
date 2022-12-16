package nl.eleven.adventofcode.puzzles.year2022.day4_campcleanup;

import com.google.common.collect.Range;
import nl.eleven.adventofcode.helpers.string.StringHelper;
import nl.eleven.adventofcode.models.pair.SimplePair;
import nl.eleven.adventofcode.tasks.IntegerSingleTask;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("year2022day4task2")
public class Task2 implements IntegerSingleTask {

	@Override
	public int executeTask(List<String> input) {
		return input.stream()
				.mapToInt(line -> {
					SimplePair<Range<Integer>> pair = StringHelper.splitAtCharacterInTwo(line, ',').map(value -> {
						SimplePair<Integer> rangePair = StringHelper.splitAtCharacterInTwo(value, '-')
								.map(Integer::parseInt);
						return Range.openClosed(rangePair.getLeft(), rangePair.getRight());
					});
					return pair.getLeft().isConnected(pair.getRight()) || pair.getRight()
							.isConnected(pair.getLeft()) ? 1 : 0;
				})
				.sum();
	}

}