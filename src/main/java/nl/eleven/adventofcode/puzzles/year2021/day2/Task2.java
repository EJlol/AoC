package nl.eleven.adventofcode.puzzles.year2021.day2;

import nl.eleven.adventofcode.helpers.inputmappers.InstructionMapper;
import nl.eleven.adventofcode.tasks.IntegerTask;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component("year2021day2task2")
public class Task2 implements IntegerTask {

	public int executeTask(List<String> input) {
		AtomicInteger depth = new AtomicInteger();
		AtomicInteger forwardDistance = new AtomicInteger();
		AtomicInteger aim = new AtomicInteger();
		InstructionMapper.map(input, Instruction.class).forEach(instruction -> {
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
