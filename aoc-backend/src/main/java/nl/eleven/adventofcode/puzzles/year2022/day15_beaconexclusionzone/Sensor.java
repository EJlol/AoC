package nl.eleven.adventofcode.puzzles.year2022.day15_beaconexclusionzone;

import com.google.common.collect.Range;
import nl.eleven.adventofcode.models.position.Position;

class Sensor extends Entity {

	private int closestBeaconDistance = Integer.MAX_VALUE;

	public Sensor(Position position) {
		super(position);
	}

	@Override
	public String toString() {
		return "S";
	}

	void updateClosestBeaconDistance(int closestBeaconDistance) {
		this.closestBeaconDistance = Math.min(this.closestBeaconDistance, closestBeaconDistance);
	}

	Range<Integer> getRangeWithoutBeacons(int row) {
		int distance = Math.abs(position.y() - row);
		if (distance >= closestBeaconDistance) {
			return null;
		}

		int columnStrength = closestBeaconDistance - distance;
		return Range.closed(position.x() - columnStrength, position.x() + columnStrength);
	}
}
