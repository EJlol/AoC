package nl.eleven.adventofcode.puzzles.year2022.day13_distresssignal;

public enum CompareResult {
	TRUE(-1),
	FALSE(1),
	EQUAL(0);

	final int value;

	CompareResult(int compareValue) {
		this.value = compareValue;
	}

	public static CompareResult compare(int left, int right) {
		if (left < right) {
			return CompareResult.TRUE;
		} else if (right < left) {
			return CompareResult.FALSE;
		}
		return CompareResult.EQUAL;
	}

	public int getValue() {
		return value;
	}
}
