package nl.eleven.adventofcode.puzzles.year2022.day10_cathoderaytube;

import nl.eleven.adventofcode.models.cpu.Cpu;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("year2022day10")
public class Task implements TaskInterface<String> {

	private static boolean isRendering(Cpu<String> cpu) {
		return (cpu.getCycleCount() % 40) >= cpu.getRegisterValue(0) &&
				(cpu.getCycleCount() % 40) <= cpu.getRegisterValue(0) + 2;
	}

	@Override
	public String executeTask1(List<String> input) {
		Cpu<String> cpu = new CpuImpl(input);
		int sum = 0;
		while (!cpu.shouldStop()) {
			cpu.doCycle();
			if ((cpu.getCycleCount() + 20) % 40 == 0) {
				sum += cpu.getCycleCount() * cpu.getRegisterValue(0);
			}
		}
		return Integer.toString(sum);
	}

	@Override
	public String executeTask2(List<String> input) {
		Cpu<String> cpu = new CpuImpl(input);
		StringBuilder builder = new StringBuilder();
		while (!cpu.shouldStop()) {
			cpu.doCycle();
			builder.append(isRendering(cpu) ? "#" : ".");
			if (cpu.getCycleCount() % 40 == 0) {
				builder.append("<br>\n");
			}
		}
		return builder.toString();
	}
}
