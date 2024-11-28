package nl.eleven.adventofcode.puzzles.year2021.day4;

import nl.eleven.adventofcode.models.position.Position;
import nl.eleven.adventofcode.models.table.Table;

public class BingoCard {

	private final Table<Integer> input;

	private final boolean[][] marked;

	BingoCard(Table<Integer> input) {
		this.input = input;

		marked = new boolean[input.getHeight()][input.getWidth()];
	}

	public int getScore() {
		int totalScore = 0;
		for (int y = 0; y < this.input.getHeight(); y++) {
			for (int x = 0; x < this.input.getWidth(); x++) {
				Integer value = input.get(x, y);
				if (value != null && !marked[y][x]) {
					totalScore += value;
				}
			}
		}
		return totalScore;
	}

	public boolean isColumnWinning(int x) {
		for (int y = 0; y < this.input.getHeight(); y++) {
			if (!marked[y][x]) {
				return false;
			}
		}
		return true;
	}

	public boolean isRowWinning(int y) {
		for (int x = 0; x < this.input.getWidth(); x++) {
			if (!marked[y][x]) {
				return false;
			}
		}
		return true;
	}

	public boolean isWinning() {
		for (int y = 0; y < this.input.getHeight(); y++) {
			if (isRowWinning(y)) {
				return true;
			}
		}
		for (int x = 0; x < this.input.getHeight(); x++) {
			if (isColumnWinning(x)) {
				return true;
			}
		}
		return false;
	}

	public void mark(int number) {
		Position position = this.input.getPositionByValue(number);
		if (position == null) {
			return;
		}

		marked[position.y()][position.x()] = true;
	}
}
