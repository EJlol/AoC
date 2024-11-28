package nl.eleven.adventofcode.puzzles.year2022.day12_climbing;

import nl.eleven.adventofcode.models.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Node {

	int cost = 0;

	int heightLevel;

	List<Node> neighbours;

	Position position;

	Node(Position position, int heightLevel) {
		this.position = position;
		this.heightLevel = heightLevel;
		neighbours = new ArrayList<>();
	}

	void addNeighbour(Node node) {
		neighbours.add(node);
	}

	int getCost() {
		return cost;
	}

	void setCost(int cost) {
		this.cost = cost;
	}

	int getHeuristic(Position position) {
		return Math.abs(this.position.x() - position.x()) + Math.abs(this.position.y() - position.y());
	}

	List<Node> getNeighbours() {
		return neighbours;
	}

	Position getPosition() {
		return position;
	}
}
