package nl.eleven.adventofcode.models.rect;

public class Rectangle {

	private int height;

	private int width;

	private int x;

	private int y;

	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}

	public Rectangle(int width, int height) {
		this.x = 0;
		this.y = 0;
		this.width = width;
		this.height = height;
	}

	public int getArea() {
		return width * height;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Rectangle growAllDirections() {
		return new Rectangle(x - 1, y - 1, width + 2, height + 2);
	}

	public void growDown() {
		this.height++;
	}

	public void growLeft() {
		this.x--;
		this.width++;
	}

	public void growRight() {
		this.width++;
	}

	public void growUp() {
		this.y--;
		this.height++;
	}

	@Override
	public String toString() {
		return String.format("(%d, %d, %d, %d)", x, y, width, height);
	}
}
