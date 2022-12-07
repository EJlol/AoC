package nl.eleven.adventofcode.puzzles.year2021.day4;

import nl.eleven.adventofcode.helpers.inputmappers.NumberMapper;
import nl.eleven.adventofcode.helpers.list.ListHelper;
import nl.eleven.adventofcode.models.table.Table;
import nl.eleven.adventofcode.tasks.IntegerTask;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("year2021day4task1")
public class Task1 implements IntegerTask {

	private static BingoCard parseBingoCard(List<String> cardInput) {
		return new BingoCard(new Table<>(cardInput.stream().map(Task1::parseBingoCardRow).toList()));
	}

	private static List<Integer> parseBingoCardRow(String rowInput) {
		return NumberMapper.map(Arrays.stream(rowInput.split(" ")).toList());
	}

	@Override
	public int executeTask(List<String> input) {
		List<Integer> numbersInput = NumberMapper.map(Arrays.stream(input.get(0).split(",")).toList());
		List<String> bingoCardsInput = input.subList(2, input.size());

		List<BingoCard> cards = ListHelper.partitionByEmptyLines(bingoCardsInput)
				.stream()
				.map(Task1::parseBingoCard)
				.toList();

		numbersInput.forEach(number -> cards.forEach(card -> card.mark(number)));

		return 0;
	}
}
