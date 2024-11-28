package nl.eleven.adventofcode.puzzles.year2015.day7_someassemblyrequired;

import java.util.Map;

public class Wire {
	private final String input1;
	private final String input2;

	private final Operations operations;

	Character signalStrength;

	Map<String, Wire> wireMap;

	public Wire(Map<String, Wire> wireMap, String identifier, Operations operations, String input1, String input2) {
		this.wireMap = wireMap;
		this.operations = operations;
		this.input1 = input1;
		this.input2 = input2;

		this.wireMap.put(identifier, this);
	}

	public Character getSignalStrength() {
		if (signalStrength != null) {
			return signalStrength;
		}

		signalStrength = switch (operations) {
			case SET -> getInputFromWire(input1);
			case AND -> (char) (getInputFromWire(input1) & getInputFromWire(input2));
			case OR -> (char) (getInputFromWire(input1) | getInputFromWire(input2));
			case NOT -> (char) (~getInputFromWire(input1));
			case LSHIFT -> (char) (getInputFromWire(input1) << getInputFromWire(input2));
			case RSHIFT -> (char) (getInputFromWire(input1) >> getInputFromWire(input2));
		};

		return signalStrength;
	}

	private Character getInputFromWire(String input) {
		try {
			return (char) Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return wireMap.get(input).getSignalStrength();
		}
	}
}
