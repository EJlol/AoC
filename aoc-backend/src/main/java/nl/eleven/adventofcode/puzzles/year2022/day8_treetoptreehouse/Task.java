package nl.eleven.adventofcode.puzzles.year2022.day8_treetoptreehouse;

import nl.eleven.adventofcode.models.table.CharacterTableMapper;
import nl.eleven.adventofcode.models.table.Table;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("year2022day8")
public class Task implements TaskInterface<Integer> {

	@Override
	public Integer executeTask1(List<String> input) {
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

	@Override
	public Integer executeTask2(List<String> input) {
		Table<Character> table = CharacterTableMapper.map(input);
		return table.mapCellsToList((cell, position) -> {
					int cellTreeHeight = Integer.parseInt("" + cell);
					long west = findTreeScore(table.getCellsWestOfPosition(position), cellTreeHeight);
					long east = findTreeScore(table.getCellsEastOfPosition(position), cellTreeHeight);
					long north = findTreeScore(table.getCellsNorthOfPosition(position), cellTreeHeight);
					long south = findTreeScore(table.getCellsSouthOfPosition(position), cellTreeHeight);
					return west * east * north * south;
				}).stream()
				.mapToInt(Long::intValue)
				.max()
				.orElse(0);
	}

	private long findTreeScore(List<Character> treeList, int cellTreeHeight) {
		long result = treeList.stream()
				.map(c -> Integer.parseInt("" + c))
				.takeWhile(treeHeight -> treeHeight < cellTreeHeight)
				.count();

		if (result != treeList.size()) {
			// Most likely it is not on the border, so we also see the blocking tree
			result++;
		}
		return result;
	}
}
