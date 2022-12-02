package nl.eleven.adventofcode.helpers.rps;

public enum RpsShape {
	ROCK,
	PAPER,
	SCISSORS;

	public WinState wins(RpsShape other) {
		if (this == other) {
			return WinState.DRAW;
		}

		return switch(this) {
			case ROCK -> other == SCISSORS ? WinState.WIN : WinState.LOSE;
			case PAPER -> other == ROCK ? WinState.WIN : WinState.LOSE;
			case SCISSORS -> other == PAPER ? WinState.WIN : WinState.LOSE;
		};
	}

	public RpsShape getOtherShapeBasedOnWinState(WinState winState) {
		if (winState == WinState.WIN) {
			return switch (this) {
				case ROCK -> RpsShape.PAPER;
				case PAPER -> RpsShape.SCISSORS;
				case SCISSORS -> RpsShape.ROCK;
			};
		} else if (winState == WinState.LOSE) {
			return switch (this) {
				case ROCK -> RpsShape.SCISSORS;
				case PAPER -> RpsShape.ROCK;
				case SCISSORS -> RpsShape.PAPER;
			};
		}

		return this;
	}
}
