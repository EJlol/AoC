package nl.eleven.adventofcode.puzzles.year2015.day7_someassemblyrequired;

enum Operations {
	AND("{input1} AND {input2} -> {output}"),
	OR("{input1} OR {input2} -> {output}"),
	NOT("NOT {input1} -> {output}"),
	LSHIFT("{input1} LSHIFT {input2} -> {output}"),
	RSHIFT("{input1} RSHIFT {input2} -> {output}"),
	SET("{input1} -> {output}");

	private final String pattern;

	Operations(String pattern) {
		this.pattern = pattern;
	}

	String getPattern() {
		return pattern;
	}
}
