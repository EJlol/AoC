package nl.eleven.adventofcode.puzzles.year2022.day18_boilingboulders;

import nl.eleven.adventofcode.models.maptable.MapTable3D;
import nl.eleven.adventofcode.models.position.Position3D;
import nl.eleven.adventofcode.tasks.IntegerDoubleTask;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiPredicate;

@Component("year2022day18")
public class Task implements IntegerDoubleTask {

	MapTable3D<Materials> map = null;

	@Override
	public int executeTask1(List<String> input) {
		map = new MapTable3D<>();
		input.stream()
				.map(Position3D::fromString)
				.forEach(p -> map.plot(p, Materials.FILLED));
		return getExposedSides((p, p2) -> map.isEmpty(p2));
	}

	@Override
	public int executeTask2(List<String> input) {
		map = new MapTable3D<>();
		input.stream()
				.map(Position3D::fromString)
				.forEach(p -> map.plot(p, Materials.FILLED));
		map.fillOutside(Materials.EMPTY);
		return getExposedSides((p, p2) -> Materials.FILLED.equals(map.get(p)) && Materials.EMPTY.equals(map.get(p2)));
	}

	public int getExposedSides(BiPredicate<Position3D, Position3D> predicate) {
		return map.streamEntries().mapToInt(entry -> {
			Position3D p = entry.getKey();

			int exposedSides = 0;
			if (predicate.test(p, p.plus(Position3D.NORTH))) {
				exposedSides++;
			}
			if (predicate.test(p, p.plus(Position3D.SOUTH))) {
				exposedSides++;
			}
			if (predicate.test(p, p.plus(Position3D.WEST))) {
				exposedSides++;
			}
			if (predicate.test(p, p.plus(Position3D.EAST))) {
				exposedSides++;
			}
			if (predicate.test(p, p.plus(Position3D.FORWARDS))) {
				exposedSides++;
			}
			if (predicate.test(p, p.plus(Position3D.BACKWARDS))) {
				exposedSides++;
			}
			return exposedSides;
		}).sum();
	}

	private enum Materials {
		FILLED,
		EMPTY
	}
}
