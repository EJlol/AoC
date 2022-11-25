package nl.eleven.adventofcode.puzzles.year2021.day2;

import nl.eleven.adventofcode.Task;
import nl.eleven.adventofcode.readers.InstructionReader;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Component("year2021day2task1")
public class Task1 implements Task {

	public int executeTask(Stream<String> stream) {
		AtomicInteger depth = new AtomicInteger();
		AtomicInteger forwardDistance = new AtomicInteger();
		new InstructionReader<Instruction>().parse(stream, Instruction.class).forEach(instruction -> {
			if (instruction.getCommand() == Command.UP) {
				depth.addAndGet(-instruction.getDistance());
			} else if (instruction.getCommand() == Command.DOWN) {
				depth.addAndGet(instruction.getDistance());
			} else if (instruction.getCommand() == Command.FORWARD) {
				forwardDistance.addAndGet(instruction.getDistance());
			}
		});

		return depth.get() * forwardDistance.get();
	}
}
