package nl.eleven.adventofcode.puzzles.year2022.day16_proboscideavolcanium;

import nl.eleven.adventofcode.helpers.string.PatternStringHelper;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component("year2022day16")
public class Task implements TaskInterface<Integer> {

	List<Tunnel> tunnels;

	@Override
	public Integer executeTask1(List<String> input) {
		tunnels = input.stream()
				.map(line -> line
						.replace("tunnels", "tunnel")
						.replace("leads", "lead")
						.replace("valves", "valve"))
				.map(simplifiedInput -> PatternStringHelper.getParameters(simplifiedInput, "Valve {name} has flow rate={flowRate}; tunnel lead to valve {tunnels}"))
				.map(parameters -> new Tunnel(parameters.get("name"), Integer.parseInt(parameters.get("flowRate")), Arrays.asList(parameters.get("tunnels").split(", "))))
				.collect(Collectors.toList());

		return findMaxFlowRate(findTunnel("AA"), 0, 0, 0);
	}

	@Override
	public Integer executeTask2(List<String> input) {
		return 0;
	}

	private int calculateFlowRate(Tunnel tunnel, int releasedPressure, int currentFlow, int time) {
		return findMaxFlowRate(tunnel, releasedPressure + currentFlow, currentFlow, time + 1);
	}

	private int findMaxFlowRate(Tunnel tunnel, int releasedPressure, int currentFlow, int time) {
		if (time >= 30) {
			return releasedPressure;
		}

		int maxFlowRate = tunnel.connections().stream()
				.mapToInt(connection -> calculateFlowRate(findTunnel(connection), releasedPressure, currentFlow, time))
				.max()
				.orElse(0);
		if (tunnel.flowRate() > 0) {
			int flowRateWithTunnelFlow = tunnel.connections().stream()
					.mapToInt(connection -> calculateFlowRate(findTunnel(connection), releasedPressure + currentFlow, currentFlow + tunnel.flowRate(), time + 2))
					.max()
					.orElse(0);
			maxFlowRate = Math.max(maxFlowRate, flowRateWithTunnelFlow);
		}
		return releasedPressure + maxFlowRate;
	}

	private Tunnel findTunnel(String name) {
		return tunnels.stream().filter(t -> t.name().equals(name)).findFirst().orElse(null);
	}

}
