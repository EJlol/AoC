package nl.eleven.adventofcode.puzzles.year2022.day13_distresssignal;

enum CompareResult {
	TRUE(-1),
	FALSE(1),
	EQUAL(0);

	final int value;

	CompareResult(int compareValue) {
		this.value = compareValue;
	}

	static CompareResult compare(int left, int right) {
		if (left < right) {
			return CompareResult.TRUE;
		} else if (right < left) {
			return CompareResult.FALSE;
		}
		return CompareResult.EQUAL;
	}

	int getValue() {
		return value;
	}
}
