package nl.eleven.adventofcode.puzzles.year2022.day11_monkeyinthemiddle;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Monkey {

	private final int falseMonkey;

	private final Queue<Long> items;

	private final Operation operation;

	private final int operationNumber;

	private final int testDivision;

	private final int trueMonkey;

	private int totalInspectedItems;

	Monkey(Queue<Long> items, Operation operation, int operationNumber, int testDivision, int trueMonkey,
		   int falseMonkey) {
		this.items = items;
		this.operation = operation;
		this.operationNumber = operationNumber;
		this.testDivision = testDivision;
		this.trueMonkey = trueMonkey;
		this.falseMonkey = falseMonkey;
		this.totalInspectedItems = 0;
	}

	void doTurn(List<Monkey> monkeys, boolean withWorry) {
		List<Long> tempItems = new ArrayList<>(items);
		for (int i = 0; i < tempItems.size(); i++) {
			long item = items.remove();
			long newItem = inspectItem(item, withWorry);
			int newMonkey = getNextMonkey(newItem);
			monkeys.get(newMonkey).addItem(newItem);
		}
	}

	int getTotalInspectedItems() {
		return totalInspectedItems;
	}

	private void addItem(long value) {
		items.add(value);
	}

	private int getNextMonkey(long value) {
		return (value % testDivision == 0) ? trueMonkey : falseMonkey;
	}

	private long inspectItem(long value, boolean withWorry) {
		totalInspectedItems++;
		value = switch (operation) {
			case TIMES -> value * operationNumber;
			case PLUS -> value + operationNumber;
			case SQUARE -> value * value;
		};
		if (withWorry) {
			value = (long) Math.floor(value / 3.0);
		}
		return value % 9699690;
	}

}
