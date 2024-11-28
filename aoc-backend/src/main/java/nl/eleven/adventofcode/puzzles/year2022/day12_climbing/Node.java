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

	public void addNeighbour(Node node) {
		neighbours.add(node);
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getHeuristic(Position position) {
		return Math.abs(this.position.x() - position.x()) + Math.abs(this.position.y() - position.y());
	}

	public List<Node> getNeighbours() {
		return neighbours;
	}

	public Position getPosition() {
		return position;
	}
}
