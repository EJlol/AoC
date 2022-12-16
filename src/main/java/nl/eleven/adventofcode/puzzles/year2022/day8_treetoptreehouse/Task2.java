package nl.eleven.adventofcode.puzzles.year2022.day8_treetoptreehouse;

import nl.eleven.adventofcode.models.table.CharacterTableMapper;
import nl.eleven.adventofcode.models.table.Table;
import nl.eleven.adventofcode.tasks.IntegerSingleTask;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("year2022day8task2")
public class Task2 implements IntegerSingleTask {

	@Override
	public int executeTask(List<String> input) {
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

	public long findTreeScore(List<Character> treeList, int cellTreeHeight) {
		long result = treeList.stream()
				.map(c -> Integer.parseInt("" + c))
				.takeWhile(treeHeight -> treeHeight < cellTreeHeight)
				.mapToInt(i -> i)
				.count();

		if (result != treeList.size()) {
			// Most likely it is not on the border, so we also see the blocking tree
			result++;
		}
		return result;
	}
}
