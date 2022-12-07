package nl.eleven.adventofcode.puzzles.year2022.day5;

import nl.eleven.adventofcode.helpers.list.ListHelper;
import nl.eleven.adventofcode.helpers.string.StringHelper;
import nl.eleven.adventofcode.models.table.Table;
import nl.eleven.adventofcode.tasks.StringTask;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@Component("year2022day5task2")
public class Task2 implements StringTask {

	@Override
	public String executeTask(List<String> input) {
		List<List<String>> inputs = ListHelper.partitionByEmptyLines(input);
		List<String> stackInput = inputs.get(0);

		List<String> instructionsInput = inputs.get(1);

		Table<String> stacks = new Table<>(stackInput.stream().map(line -> {
			return List.of(
					line.substring(1, 2),
					line.substring(5, 6),
					line.substring(9, 10),
					line.substring(13, 14),
					line.substring(17, 18),
					line.substring(21, 22),
					line.substring(25, 26),
					line.substring(29, 30),
					line.substring(33, 34));
		}).toList())
				.rotate()
				.flipHorizontally();

		stacks.removeFirstColumn();
		List<Stack<String>> mappedStacks = stacks.mapRows(row -> {
			Stack<String> stack = new Stack<>();
			row.forEach(s -> {
				if (!s.equals(" ")) {
					stack.push(s);
				}
			});
			return stack;
		});

		instructionsInput.forEach(instruction -> {
			List<String> params = StringHelper.splitAtCharacter(instruction, ' ');
			int amount = Integer.parseInt(params.get(1));
			int startPosition = Integer.parseInt(params.get(3)) - 1;
			int destinationPosition = Integer.parseInt(params.get(5)) - 1;

			Stack<String> tempStack = new Stack<>();
			for (int i = 0; i < amount; i++) {
				tempStack.push(mappedStacks.get(startPosition).pop());
			}
			for (int i = 0; i < amount; i++) {
				mappedStacks.get(destinationPosition).push(tempStack.pop());
			}
		});

		return Arrays.toString(mappedStacks.stream().map(Stack::pop).toList().toArray());
	}

}
