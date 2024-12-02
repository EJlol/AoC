package nl.eleven.adventofcode.models.table;

import nl.eleven.adventofcode.models.position.Position;
import nl.eleven.adventofcode.models.rect.Rectangle;

public class ColumnSpan {

	private final int row;

	private int width;

	private int x;

	public ColumnSpan(int row, int x, int width) {
		this.row = row;
		this.x = x;
		this.width = width;
	}

	public Rectangle getRectangle() {
		return new Rectangle(x, row, width, 1);
	}

	public int getRightX() {
		return x + width;
	}

	public int getRow() {
		return row;
	}

	public int getWidth() {
		return width;
	}

	public int getX() {
		return x;
	}

	public void growLeft() {
		this.x--;
		this.width++;
	}

	public void growRight() {
		this.width++;
	}

	public boolean isNeighbourPosition(Position position) {
		return (position.y() - 1 == row || position.y() == row || position.y() + 1 == row) &&
				(position.x() >= x - 1 && position.x() < getRightX() + 1);
	}
}
