package nl.eleven.adventofcode.puzzles.year2022.day14_regolithreservoir;

enum Materials {
	ROCK('#'),
	SOURCE('+'),
	SAND('o');

	final char c;

	Materials(char c) {
		this.c = c;
	}

	@Override
	public String toString() {
		return "" + this.c;
	}
}
