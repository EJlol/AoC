package nl.eleven.adventofcode.puzzles.year2023.day4_scratchcards;

import java.util.List;

class ScratchCard {

	private final List<Integer> cardNumbers;

	private final int id;

	private final Integer isCopyOfId;

	private final List<Integer> winningNumbers;

	ScratchCard(Integer isCopyOfId, int id, List<Integer> cardNumbers, List<Integer> winningNumbers) {
		this.isCopyOfId = isCopyOfId;
		this.id = id;
		this.cardNumbers = cardNumbers;
		this.winningNumbers = winningNumbers;
	}

	int getId() {
		return id;
	}

	List<Integer> getNextCardNumbers() {
		int offset = isCopyOfId == null ? 0 : id;
		return cardNumbers.stream().filter(winningNumbers::contains).map(n -> n + offset).toList();
	}

	List<Integer> getWinningNumbers() {
		return winningNumbers;
	}
}
