package nl.eleven.adventofcode.puzzles.year2021.day2;

import nl.eleven.adventofcode.Task;
import nl.eleven.adventofcode.mappers.InstructionMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component("year2021day2task1")
public class Task1 implements Task {

	public int executeTask(List<String> input) {
		AtomicInteger depth = new AtomicInteger();
		AtomicInteger forwardDistance = new AtomicInteger();
		new InstructionMapper<Instruction>().map(input, Instruction.class).forEach(instruction -> {
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
