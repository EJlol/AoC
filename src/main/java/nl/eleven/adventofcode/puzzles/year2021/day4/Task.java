package nl.eleven.adventofcode.puzzles.year2021.day4;

import nl.eleven.adventofcode.helpers.list.PartitionListBy;
import nl.eleven.adventofcode.models.table.Table;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component("year2021day4")
public class Task implements TaskInterface<Integer> {

	private static BingoCard parseBingoCard(List<String> cardInput) {
		return new BingoCard(new Table<>(cardInput.stream().map(Task::parseBingoCardRow).toList()));
	}

	private static List<Integer> parseBingoCardRow(String rowInput) {
		return Arrays.stream(rowInput.split(" "))
				.map(Integer::valueOf)
				.collect(Collectors.toList());
	}

	@Override
	public Integer executeTask1(List<String> input) {
		List<Integer> numbersInput = Arrays.stream(input.getFirst().split(","))
				.map(Integer::valueOf)
				.toList();
		List<String> bingoCardsInput = input.subList(2, input.size());

		List<BingoCard> cards = PartitionListBy.emptyLines(bingoCardsInput)
				.stream()
				.map(Task::parseBingoCard)
				.toList();

		numbersInput.forEach(number -> cards.forEach(card -> card.mark(number)));

		return 0;
	}

	@Override
	public Integer executeTask2(List<String> input) {
		return 0;
	}
}
