package nl.eleven.adventofcode.puzzles.year2022.day8_treetoptreehouse;

import nl.eleven.adventofcode.models.table.CharacterTableMapper;
import nl.eleven.adventofcode.models.table.Table;
import nl.eleven.adventofcode.tasks.IntegerSingleTask;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("year2022day8task1")
public class Task1 implements IntegerSingleTask {

	@Override
	public int executeTask(List<String> input) {
		Table<Character> table = CharacterTableMapper.map(input);
		return table.filterCells((cell, position) -> {
			int cellTreeHeight = Integer.parseInt("" + cell);
			boolean west = table.getCellsWestOfPosition(position).stream()
					.mapToInt(c -> Integer.parseInt("" + c))
					.max().orElse(-1) < cellTreeHeight;
			boolean east = table.getCellsEastOfPosition(position).stream()
					.mapToInt(c -> Integer.parseInt("" + c))
					.max().orElse(-1) < cellTreeHeight;
			boolean north = table.getCellsNorthOfPosition(position).stream()
					.mapToInt(c -> Integer.parseInt("" + c))
					.max().orElse(-1) < cellTreeHeight;
			boolean south = table.getCellsSouthOfPosition(position).stream()
					.mapToInt(c -> Integer.parseInt("" + c))
					.max().orElse(-1) < cellTreeHeight;
			return west || east || north || south;
		}).size();
	}
}
