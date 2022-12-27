package nl.eleven.adventofcode.puzzles.year2022.day15_beaconexclusionzone;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;
import nl.eleven.adventofcode.models.maptable.MapTable;
import nl.eleven.adventofcode.models.position.Position;
import nl.eleven.adventofcode.tasks.IntegerDoubleTask;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component("year2022day15")
public class Task implements IntegerDoubleTask {

	MapTable<Beacon> map = null;

	@Override
	public int executeTask1(List<String> input) {
		map = new MapTable<>();
		input.forEach(lineInput -> {
			int sensorX = Integer.parseInt(lineInput.substring(lineInput.indexOf("=") + 1, lineInput.indexOf(",")));
			int sensorY = Integer.parseInt(lineInput.substring(lineInput.indexOf(",") + 4, lineInput.indexOf(":")));

			String beaconInput = lineInput.split(":")[1];
			int beaconX = Integer.parseInt(beaconInput.substring(beaconInput.indexOf("=") + 1, beaconInput.indexOf(",")));
			int beaconY = Integer.parseInt(beaconInput.substring(beaconInput.indexOf(",") + 4));

			Beacon beacon = map.getOrDefault(sensorX, sensorY, new Beacon(new Position(sensorX, sensorY)));
			beacon.updateStrength(new Position(sensorX, sensorY).manhattanDistance(new Position(beaconX, beaconY)));
			map.plot(sensorX, sensorY, beacon);
		});
		return getAmountPositionsWithBeaconSignal();
	}

	@Override
	public int executeTask2(List<String> input) {
		map = new MapTable<>();
		return 0;
	}

	private int getAmountPositionsWithBeaconSignal() {
		RangeSet<Integer> rangeSet = TreeRangeSet.create();
		rangeSet.addAll(map.stream()
				.map(beacon -> beacon.getRangeForRow(10))
				.filter(Objects::nonNull)
				.toList());

		rangeSet.asRanges().forEach(System.out::println);

		return rangeSet.asRanges().stream()
				.mapToInt(r -> r.upperEndpoint() - r.lowerEndpoint())
				.sum();
	}

	private static class Beacon {

		private final Position position;

		private int strength;

		public Beacon(Position position) {
			this.position = position;
		}

		private Range<Integer> getRangeForRow(int row) {
			if (position.manhattanDistance(new Position(position.x(), row)) >= strength) {
				return null;
			}

			int rowStrength = Math.abs(position.y() - row) + 2;
			return Range.closed((position.x() - rowStrength), (position.x() + rowStrength));
		}

		private void updateStrength(int strength) {
			this.strength = Math.max(this.strength, strength);
		}
	}
}
