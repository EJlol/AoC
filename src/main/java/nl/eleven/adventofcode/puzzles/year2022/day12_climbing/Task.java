package nl.eleven.adventofcode.puzzles.year2022.day12_climbing;

import nl.eleven.adventofcode.models.Position;
import nl.eleven.adventofcode.models.table.CharacterTableMapper;
import nl.eleven.adventofcode.models.table.Table;
import nl.eleven.adventofcode.tasks.StringDoubleTask;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

@Component("year2022day12")
public class Task implements StringDoubleTask {

	Node letterENode = null;

	Node letterSNode = null;

	List<Node> lowestElevationLevel = new ArrayList<>();

	private static void generateNeighbourConnections(Node[][] map, Node node, BiPredicate<Node, Node> fn) {
		Position p = node.getPosition();
		if (p.x() > 0) {
			Node westNeighbour = map[p.x() - 1][p.y()];
			if (fn.test(node, westNeighbour)) {
				westNeighbour.addNeighbour(node);
			}
			if (fn.test(westNeighbour, node)) {
				node.addNeighbour((westNeighbour));
			}
		}
		if (p.y() > 0) {
			Node northNeighbour = map[p.x()][p.y() - 1];
			if (fn.test(node, northNeighbour)) {
				northNeighbour.addNeighbour(node);
			}
			if (fn.test(northNeighbour, node)) {
				node.addNeighbour(northNeighbour);
			}
		}
	}

	private static int getHeightLevel(Character c) {
		if (c == 'S') {
			return 0;
		} else if (c == 'E') {
			return 26;
		}
		return c - 97;
	}

	@Override
	public String executeTask1(List<String> input) {
		Table<Character> table = CharacterTableMapper.map(input);

		generateMap(table, (toNode, fromNode) -> toNode.heightLevel <= fromNode.heightLevel + 1);
		calculateWalkingRoute(letterSNode, n -> letterENode.equals(n));
		return Integer.toString(letterENode.getCost());
	}

	@Override
	public String executeTask2(List<String> input) {
		Table<Character> table = CharacterTableMapper.map(input);

		generateMap(table, (toNode, fromNode) -> fromNode.heightLevel - 1 <= toNode.heightLevel);
		Node r = calculateWalkingRoute(letterENode, n -> n.heightLevel == 0);
		return "" + (r.getCost() - 2); // FIXME
	}

	private Node calculateWalkingRoute(Node startNode, Predicate<Node> destination) {
		List<Node> openList = new ArrayList<>();
		List<Node> visitedList = new ArrayList<>();
		startNode.setCost(0);
		openList.add(startNode);

		Node currentNode = startNode;
		while (!destination.test(currentNode) && !openList.isEmpty()) {
			currentNode = openList.remove(0);
			visitedList.add(currentNode);

			Node finalCurrentNode = currentNode;
			currentNode.getNeighbours().forEach(n -> {
				if (!openList.contains(n) && !visitedList.contains(n)) {
					openList.add(n);
				}
				if (n.getCost() == 0 || n.getCost() > finalCurrentNode.getCost() + 1) {
					n.setCost(finalCurrentNode.getCost() + 1);
				}
			});
		}
		return currentNode;
	}

	private Node[][] generateMap(Table<Character> table, BiPredicate<Node, Node> fn) {
		Node[][] map = new Node[table.getWidth()][table.getHeight()];
		table.mapCellsToArray((c, p) -> generateNode(map, c, p, fn), map);
		return map;
	}

	private Node generateNode(Node[][] map, Character c, Position position, BiPredicate<Node, Node> fn) {
		int heightLevel = getHeightLevel(c);
		Node node = new Node(position, heightLevel);
		generateNeighbourConnections(map, node, fn);

		if (c == 'S') {
			letterSNode = node;
		} else if (c == 'E') {
			letterENode = node;
		}
		if (heightLevel == 0) {
			lowestElevationLevel.add(node);
		}
		return node;
	}
}
