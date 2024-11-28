package nl.eleven.adventofcode.puzzles.year2022.day15_beaconexclusionzone;

import nl.eleven.adventofcode.models.position.Position;

class Beacon extends Entity {

	public Beacon(Position position) {
		super(position);
	}

	@Override
	public String toString() {
		return "B";
	}
}
