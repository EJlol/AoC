package nl.eleven.adventofcode.puzzles.year2022.day15_beaconexclusionzone;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;
import nl.eleven.adventofcode.helpers.string.ParameterStringHelper;
import nl.eleven.adventofcode.models.maptable.MapTable;
import nl.eleven.adventofcode.models.position.Position;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component("year2022day15")
public class Task implements TaskInterface<Long> {

	private static final int ROW_NUMBER = 2000000;

	MapTable<Object> map = null;

	@Override
	public Long executeTask1(List<String> input) {
		initializeMap(input);
		return getAmountPositionsWithBeaconSignal();
	}

	@Override
	public Long executeTask2(List<String> input) {
		initializeMap(input);
		for(int y = 0; y < 4000000; y++) {
			Set<Range<Integer>> rangeSet = findRangesForRow(y)
					.subRangeSet(Range.closed(0, 4000000))
					.complement()
					.asRanges()
					.stream()
					.filter(r -> r.hasLowerBound() && r.hasUpperBound() && (r.upperEndpoint() - r.lowerEndpoint()) > 1)
					.collect(Collectors.toSet());

			Optional<Range<Integer>> optionalResult = rangeSet.stream().findAny();
			if (optionalResult.isPresent()) {
				int x = optionalResult.get().lowerEndpoint() + 1;
				return (x * 4000000L) + y;
			}
		}
		return -1L;
	}

	private RangeSet<Integer> findRangesForRow(int rowNumber) {
		RangeSet<Integer> rangeSet = TreeRangeSet.create();
		rangeSet.addAll(map.stream()
				.filter(Sensor.class::isInstance)
				.map(sensor -> ((Sensor) sensor).getRangeWithoutBeacons(rowNumber))
				.filter(Objects::nonNull)
				.toList());
		return rangeSet;
	}

	private Long getAmountPositionsWithBeaconSignal() {
		RangeSet<Integer> rangeSet = findRangesForRow(Task.ROW_NUMBER);

		return rangeSet.asRanges().stream()
				.mapToLong(r -> r.upperEndpoint() - r.lowerEndpoint())
				.sum();
	}

	private void initializeMap(List<String> input) {
		map = new MapTable<>();
		input.forEach(lineInput -> {
			Map<String, String> parameters = ParameterStringHelper.getParameters(lineInput, "Sensor at x={sensorX}, y={sensorY}: closest beacon is at x={beaconX}, y={beaconY}");

			Position sensorPosition = new Position(Integer.parseInt(parameters.get("sensorX")), Integer.parseInt(parameters.get("sensorY")));
			Position beaconPosition = new Position(Integer.parseInt(parameters.get("beaconX")), Integer.parseInt(parameters.get("beaconY")));

			Sensor sensor = (Sensor) map.getOrDefault(sensorPosition, new Sensor(sensorPosition));
			sensor.updateClosestBeaconDistance(sensorPosition.manhattanDistance(beaconPosition));

			Beacon beacon = (Beacon) map.getOrDefault(beaconPosition, new Beacon(beaconPosition));

			map.plot(sensorPosition, sensor);
			map.plot(beaconPosition, beacon);
		});
	}

	private static class Entity {

		protected final Position position;

		public Entity(Position p) {
			position = p;
		}
	}

	private static class Beacon extends Entity {
		public Beacon(Position position) {
			super(position);
		}

		@Override
		public String toString() {
			return "B";
		}
	}

	private static class Sensor extends Entity {

		private int closestBeaconDistance = Integer.MAX_VALUE;

		public Sensor(Position position) {
			super(position);
		}

		@Override
		public String toString() {
			return "S";
		}

		private void updateClosestBeaconDistance(int closestBeaconDistance) {
			this.closestBeaconDistance = Math.min(this.closestBeaconDistance, closestBeaconDistance);
		}

		private Range<Integer> getRangeWithoutBeacons(int row) {
			int distance = Math.abs(position.y() - row);
			if (distance >= closestBeaconDistance) {
				return null;
			}

			int columnStrength = closestBeaconDistance - distance;
			return Range.closed(position.x() - columnStrength, position.x() + columnStrength);
		}
	}
}
