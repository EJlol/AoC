package nl.eleven.adventofcode.puzzles.year2021.day2;

class Submarine {

	int aim;

	int depth;

	int forwardDistance;

	public void decreaseAim(int delta) {
		this.aim -= delta;
	}

	public void decreaseDepth(int delta) {
		this.depth -= delta;
	}

	public void forward(int delta) {
		this.forwardDistance += delta;
		this.depth += this.aim * delta;
	}

	public int getPosition() {
		return this.depth * this.forwardDistance;
	}

	public void increaseAim(int delta) {
		this.aim += delta;
	}

	public void increaseDepth(int delta) {
		this.depth += delta;
	}
}
