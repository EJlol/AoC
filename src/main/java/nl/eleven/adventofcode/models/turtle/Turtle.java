package nl.eleven.adventofcode.models.turtle;

import nl.eleven.adventofcode.models.direction.Direction;
import nl.eleven.adventofcode.models.position.Position;

public class Turtle {

	Position position;

	public Turtle(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return this.position;
	}

	public void move(String input) {
		Direction direction = Direction.of(input);
		this.position = this.position.plus(direction.getPosition());
	}

	public void moveEast() {
		this.position = this.position.plus(Position.EAST);
	}

	public void moveNorth() {
		this.position = this.position.plus(Position.NORTH);
	}

	public void moveSouth() {
		this.position = this.position.plus(Position.SOUTH);
	}

	public void moveWest() {
		this.position = this.position.plus(Position.WEST);
	}
}
