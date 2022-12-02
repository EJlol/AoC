package nl.eleven.adventofcode.rps;

import java.util.EnumMap;
import java.util.Map;

import static nl.eleven.adventofcode.rps.RpsShape.PAPER;
import static nl.eleven.adventofcode.rps.RpsShape.ROCK;
import static nl.eleven.adventofcode.rps.RpsShape.SCISSORS;

public class RpsMatch {
	private final EnumMap<RpsShape, RpsShape> wins = new EnumMap<>(Map.of(
			ROCK, SCISSORS,
			PAPER, ROCK,
			SCISSORS, ROCK
	));

	private final EnumMap<RpsShape, RpsShape> loses = new EnumMap<>(Map.of(
			ROCK, PAPER,
			PAPER, SCISSORS,
			SCISSORS, ROCK
	));

	private final RpsShape you;
	private final RpsShape opponent;

	public RpsMatch(RpsShape you, RpsShape opponent) {
		this.you = you;
		this.opponent = opponent;
	}

	public RpsMatch(RpsShape opponent, WinState winState) {
		this.opponent = opponent;
		if (winState == WinState.WIN) {
			you = loses.get(opponent);
		} else if (winState == WinState.LOSE) {
			you = wins.get(opponent);
		} else {
			you = opponent;
		}
	}

	public RpsShape getYou() {
		return you;
	}

	public RpsShape getOpponent() {
		return opponent;
	}

	public boolean isPlayerWinning() {
		return wins.get(you) == opponent;
	}

	public boolean isOpponentWinning() {
		return wins.get(opponent) == you;
	}

	public boolean isDraw() {
		return you == opponent;
	}

	public WinState getResult() {
		if (this.isPlayerWinning()) {
			return WinState.WIN;
		} else if (this.isOpponentWinning()) {
			return WinState.LOSE;
		}
		return WinState.DRAW;
	}
}
