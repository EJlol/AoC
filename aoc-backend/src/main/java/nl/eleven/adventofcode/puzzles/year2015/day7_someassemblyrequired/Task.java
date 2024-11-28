package nl.eleven.adventofcode.puzzles.year2015.day7_someassemblyrequired;

import nl.eleven.adventofcode.helpers.string.MultiPatternStringHelper;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component("year2015day7")
public class Task implements TaskInterface<Long> {

	private static MultiPatternStringHelper<Operations> initializeMultiPatternStringHelper() {
		MultiPatternStringHelper<Operations> multiPatternStringHelper = new MultiPatternStringHelper<>();
		for (Operations op : Operations.values()) {
			multiPatternStringHelper.addPattern(op, op.getPattern());
		}
		return multiPatternStringHelper;
	}

	private static HashMap<String, Wire> initializeWiremap(List<String> input) {
		MultiPatternStringHelper<Operations> multiPatternStringHelper = initializeMultiPatternStringHelper();
		HashMap<String, Wire> wireMap = new HashMap<>();
		input.forEach(instruction -> {
			MultiPatternStringHelper.PatternResult<Operations> result = multiPatternStringHelper.getParameters(instruction);
			new Wire(wireMap, result.parameters().get("output"), result.key(), result.parameters().get("input1"), result.parameters().get("input2"));
		});
		return wireMap;
	}

	@Override
	public Long executeTask1(List<String> input) {
		HashMap<String, Wire> wireMap = initializeWiremap(input);
		return (long) wireMap.get("a").getSignalStrength();
	}

	@Override
	public Long executeTask2(List<String> input) {
		HashMap<String, Wire> wireMap = initializeWiremap(input);
		Character oldStrength = wireMap.get("a").getSignalStrength();
		wireMap.values().forEach(wire -> wire.signalStrength = null);
		wireMap.get("b").signalStrength = oldStrength;

		return (long) wireMap.get("a").getSignalStrength();
	}
}
