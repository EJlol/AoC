package nl.eleven.adventofcode.puzzles.year2021.day2;

import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("year2021day2")
public class Task implements TaskInterface<Integer> {

	public Integer executeTask1(List<String> input) {
		Submarine submarine = new Submarine();
		List<Instruction> instructions = input.stream()
				.map(Instruction::new)
				.toList();

		instructions.forEach(instruction -> {
			int delta = instruction.getDistance();
			switch (instruction.getCommand()) {
				case UP -> submarine.decreaseDepth(delta);
				case DOWN -> submarine.increaseDepth(delta);
				case FORWARD -> submarine.forward(delta);
			}
		});

		return submarine.getPosition();
	}

	public Integer executeTask2(List<String> input) {
		Submarine submarine = new Submarine();
		List<Instruction> instructions = input.stream()
				.map(Instruction::new)
				.toList();

		instructions.forEach(instruction -> {
			int delta = instruction.getDistance();
			switch (instruction.getCommand()) {
				case UP -> submarine.decreaseAim(delta);
				case DOWN -> submarine.increaseAim(delta);
				case FORWARD -> submarine.forward(delta);
			}
		});

		return submarine.getPosition();
	}

	static class Submarine {

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
}
