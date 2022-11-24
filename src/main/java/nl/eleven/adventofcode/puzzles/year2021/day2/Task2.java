package nl.eleven.adventofcode.puzzles.year2021.day2;

import nl.eleven.adventofcode.Task;
import nl.eleven.adventofcode.readers.InstructionReader;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component("year2021day2task2")
public class Task2 implements Task {

	private static final String URL = "https://adventofcode.com/2021/day/2/input";

	public int executeTask() {
		AtomicInteger depth = new AtomicInteger();
		AtomicInteger forwardDistance = new AtomicInteger();
		AtomicInteger aim = new AtomicInteger();
		new InstructionReader<Instruction>().read(URL, Instruction.class).forEach(instruction -> {
			if (instruction.getCommand() == Command.UP) {
				aim.addAndGet(-instruction.getDistance());
			} else if (instruction.getCommand() == Command.DOWN) {
				aim.addAndGet(instruction.getDistance());
			} else if (instruction.getCommand() == Command.FORWARD) {
				forwardDistance.addAndGet(instruction.getDistance());
				depth.addAndGet(aim.get() * instruction.getDistance());
			}
		});

		return depth.get() * forwardDistance.get();
	}
}
