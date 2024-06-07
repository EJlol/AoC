package nl.eleven.adventofcode.puzzles.year2022.day18_boilingboulders;

import nl.eleven.adventofcode.models.maptable.MapTable3D;
import nl.eleven.adventofcode.models.position.Position3D;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiPredicate;

@Component("year2022day18")
public class Task implements TaskInterface<Integer> {

	MapTable3D<Materials> map = null;

	/**
	 * Executes the first task.
	 * This method initializes a new 3D map and plots the positions from the input list as FILLED.
	 * It then calculates and returns the number of exposed sides in the map.
	 * An exposed side is defined as a side that is adjacent to an empty position.
	 *
	 * @param input A list of strings representing the positions to be plotted on the map.
	 * @return The number of exposed sides in the map.
	 */
	@Override
	public Integer executeTask1(List<String> input) {
		map = new MapTable3D<>();
		input.stream()
				.map(Position3D::fromString)
				.forEach(p -> map.plot(p, Materials.FILLED));
		return getExposedSides((_, p2) -> map.isEmpty(p2));
	}

	/**
	 * Executes the second task.
	 * This method initializes a new 3D map and plots the positions from the input list as FILLED.
	 * It then fills the outside of the map with EMPTY materials.
	 * Finally, it calculates and returns the number of exposed FILLED sides in the map.
	 * An exposed FILLED side is defined as a side that is adjacent to an EMPTY position.
	 *
	 * @param input A list of strings representing the positions to be plotted on the map.
	 * @return The number of exposed FILLED sides in the map.
	 */
	@Override
	public Integer executeTask2(List<String> input) {
		map = new MapTable3D<>();
		input.stream()
				.map(Position3D::fromString)
				.forEach(p -> map.plot(p, Materials.FILLED));
		map.fillOutside(Materials.EMPTY);
		return getExposedSides((p, p2) -> Materials.FILLED.equals(map.get(p)) && Materials.EMPTY.equals(map.get(p2)));
	}

	public int getExposedSides(BiPredicate<Position3D, Position3D> predicate) {
		List<Position3D> directions = List.of(Position3D.NORTH, Position3D.SOUTH, Position3D.WEST, Position3D.EAST, Position3D.FORWARDS, Position3D.BACKWARDS);

		return map.streamEntries().mapToInt(entry -> {
			Position3D position = entry.getKey();
			return (int) directions.stream()
					.filter(direction -> predicate.test(position, position.plus(direction)))
					.count();
		}).sum();
	}

	private enum Materials {
		FILLED,
		EMPTY
	}
}
