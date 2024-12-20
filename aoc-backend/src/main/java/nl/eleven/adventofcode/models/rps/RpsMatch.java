package nl.eleven.adventofcode.models.rps;

import java.util.EnumMap;
import java.util.Map;

public class RpsMatch {

	private final EnumMap<RpsShape, RpsShape> loses = new EnumMap<>(Map.of(
			RpsShape.ROCK, RpsShape.PAPER,
			RpsShape.PAPER, RpsShape.SCISSORS,
			RpsShape.SCISSORS, RpsShape.ROCK
	));

	private final RpsShape opponent;

	private final EnumMap<RpsShape, RpsShape> wins = new EnumMap<>(Map.of(
			RpsShape.ROCK, RpsShape.SCISSORS,
			RpsShape.PAPER, RpsShape.ROCK,
			RpsShape.SCISSORS, RpsShape.PAPER
	));

	private final RpsShape you;

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

	public RpsShape getOpponent() {
		return opponent;
	}

	public WinState getResult() {
		if (this.isPlayerWinning()) {
			return WinState.WIN;
		} else if (this.isOpponentWinning()) {
			return WinState.LOSE;
		}
		return WinState.DRAW;
	}

	public RpsShape getYou() {
		return you;
	}

	public boolean isDraw() {
		return you == opponent;
	}

	public boolean isOpponentWinning() {
		return wins.get(opponent) == you;
	}

	public boolean isPlayerWinning() {
		return wins.get(you) == opponent;
	}
}
