package nl.eleven.adventofcode.rps;

import java.util.Arrays;

public enum WinState {
	WIN("Z", 6),
	DRAW("Y", 3),
	LOSE("X", 0);

	private final String letter;
	private final int score;

	WinState(String letter, int score) {
		this.letter = letter;
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public static WinState of(String letter) {
		return Arrays.stream(WinState.values())
				.filter(value -> value.letter.equals(letter))
				.findAny()
				.orElse(null);
	}
}
