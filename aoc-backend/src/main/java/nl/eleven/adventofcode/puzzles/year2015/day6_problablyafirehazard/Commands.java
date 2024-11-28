package nl.eleven.adventofcode.puzzles.year2015.day6_problablyafirehazard;

import java.util.function.Function;

enum Commands {
	TOGGLE(l -> l == 1 ? 0 : 1, l -> l + 2),
	TURN_ON(l -> 1, l -> l + 1),
	TURN_OFF(l -> 0, l -> Math.max(l - 1, 0));

	private final Function<Integer, Integer> actionTask1;

	private final Function<Integer, Integer> actionTask2;

	Commands(Function<Integer, Integer> actionTask1, Function<Integer, Integer> actionTask2) {
		this.actionTask1 = actionTask1;
		this.actionTask2 = actionTask2;
	}

	Function<Integer, Integer> getActionTask1() {
		return actionTask1;
	}

	Function<Integer, Integer> getActionTask2() {
		return actionTask2;
	}
}
