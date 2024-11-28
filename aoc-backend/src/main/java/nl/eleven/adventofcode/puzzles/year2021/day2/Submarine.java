package nl.eleven.adventofcode.puzzles.year2021.day2;

class Submarine {

	int aim;

	int depth;

	int forwardDistance;

	void decreaseAim(int delta) {
		this.aim -= delta;
	}

	void decreaseDepth(int delta) {
		this.depth -= delta;
	}

	void forward(int delta) {
		this.forwardDistance += delta;
		this.depth += this.aim * delta;
	}

	int getPosition() {
		return this.depth * this.forwardDistance;
	}

	void increaseAim(int delta) {
		this.aim += delta;
	}

	void increaseDepth(int delta) {
		this.depth += delta;
	}
}
