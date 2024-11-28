package nl.eleven.adventofcode.puzzles.year2022.day11_monkeyinthemiddle;

import nl.eleven.adventofcode.helpers.list.ListHelper;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component("year2022day11")
public class Task implements TaskInterface<Long> {

	ArrayList<Monkey> monkeys = new ArrayList<>();

	public void doRound(boolean withWorry) {
		monkeys.forEach(monkey -> monkey.doTurn(monkeys, withWorry));
	}

	@Override
	public Long executeTask1(List<String> input) {
		return playGame(20, true);
	}

	@Override
	public Long executeTask2(List<String> input) {
		return playGame(10_000, false);
	}

	public long playGame(int rounds, boolean withWorry) {
		prepareMonkeys();
		for (int i = 0; i < rounds; i++) {
			doRound(withWorry);
		}
		List<Integer> totalInspectedItems = ListHelper.max(monkeys.stream()
				.map(Monkey::getTotalInspectedItems)
				.toList(), 2);
		return (long) totalInspectedItems.get(0) * (long) totalInspectedItems.get(1);
	}

	private void prepareMonkeys() {
		monkeys = new ArrayList<>();
		monkeys.add(new Monkey(new LinkedList<>(List.of(54L, 53L)), Operation.TIMES, 3, 2, 2, 6));
		monkeys.add(new Monkey(new LinkedList<>(List.of(95L, 88L, 75L, 81L, 91L, 67L, 65L, 84L)), Operation.TIMES, 11, 7, 3, 4));
		monkeys.add(new Monkey(new LinkedList<>(List.of(76L, 81L, 50L, 93L, 96L, 81L, 83L)), Operation.PLUS, 6, 3, 5, 1));
		monkeys.add(new Monkey(new LinkedList<>(List.of(83L, 85L, 85L, 63L)), Operation.PLUS, 4, 11, 7, 4));
		monkeys.add(new Monkey(new LinkedList<>(List.of(85L, 52L, 64L)), Operation.PLUS, 8, 17, 0, 7));
		monkeys.add(new Monkey(new LinkedList<>(List.of(57L)), Operation.PLUS, 2, 5, 1, 3));
		monkeys.add(new Monkey(new LinkedList<>(List.of(60L, 95L, 76L, 66L, 91L)), Operation.SQUARE, 0, 13, 2, 5));
		monkeys.add(new Monkey(new LinkedList<>(List.of(65L, 84L, 76L, 72L, 79L, 65L)), Operation.PLUS, 5, 19, 6, 0));
	}
}
