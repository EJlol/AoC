package nl.eleven.adventofcode.puzzles.year2023.day4_scratchcards;

import nl.eleven.adventofcode.helpers.pattern.PatternStringHelper;
import nl.eleven.adventofcode.helpers.string.StringSplitter;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("year2023day4")
public class Task implements TaskInterface<Integer> {

	private final static String TEMPLATE = "Card {id}: {winningNumbers} | {cardNumbers}";

	private static void addCardToResult(Map<Integer, Integer> results, Integer nextCardNumber) {
		results.put(nextCardNumber, results.getOrDefault(nextCardNumber, 0) + 1);
	}

	private static void extractWinnings(Integer isCopyOfId, Integer id, ScratchCard card, Map<Integer, Integer> results,
										Map<Integer, ScratchCard> cards) {
		List<Integer> nextCardNumbers = card.getNextCardNumbers();
		addCardToResult(results, id);
		nextCardNumbers.forEach(nextCardNumber -> {
			int idOfNextCard = isCopyOfId == null ? nextCardNumber : isCopyOfId + nextCardNumber;
			if (!cards.containsKey(idOfNextCard)) {
				return;
			}

			addCardToResult(results, idOfNextCard);

			ScratchCard nextCard2 = new ScratchCard(id, idOfNextCard, card.getNextCardNumbers(), card.getWinningNumbers());
			extractWinnings(id, nextCardNumber, nextCard2, results, cards);
		});
	}

	@Override
	public Integer executeTask1(List<String> input) {
		return input.stream().mapToInt(line -> {
			Map<String, String> parameters = PatternStringHelper.getParameters(line, TEMPLATE);
			List<Integer> winningNumbers = StringSplitter.splitAtWhitespace(parameters.get("winningNumbers")).stream()
					.map(Integer::parseInt).toList();
			List<Integer> cardNumbers = StringSplitter.splitAtWhitespace(parameters.get("cardNumbers")).stream()
					.map(Integer::parseInt).toList();

			int amountWinningCardNumbers = cardNumbers.stream().filter(winningNumbers::contains).toList().size();
			return (int) Math.pow(2, amountWinningCardNumbers - 1);
		}).sum();
	}

	@Override
	public Integer executeTask2(List<String> input) {
		Map<Integer, ScratchCard> cards = input.stream().map(line -> {
			Map<String, String> parameters = PatternStringHelper.getParameters(line, TEMPLATE);
			List<Integer> winningNumbers = StringSplitter.splitAtWhitespace(parameters.get("winningNumbers")).stream()
					.map(Integer::parseInt).toList();
			List<Integer> cardNumbers = StringSplitter.splitAtWhitespace(parameters.get("cardNumbers")).stream()
					.map(Integer::parseInt).toList();

			return new ScratchCard(null, Integer.parseInt(parameters.get("id").trim()), cardNumbers, winningNumbers);
		}).collect(Collectors.toMap(ScratchCard::getId, Function.identity()));

		Map<Integer, Integer> results = new HashMap<>();

		cards.forEach((id, card) -> {
			extractWinnings(null, id, card, results, cards);
		});

		return results.values().stream().mapToInt(i -> i).sum();
	}
}
