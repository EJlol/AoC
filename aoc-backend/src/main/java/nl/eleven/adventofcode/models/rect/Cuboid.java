package nl.eleven.adventofcode.models.rect;

import java.util.Collections;
import java.util.List;

public class Cuboid {

	private int depth;

	private int height;

	private int width;

	private int x;

	private int y;

	private int z;

	public Cuboid(int x, int y, int z, int width, int height, int depth) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.height = height;
		this.depth = depth;
	}

	public Cuboid(int width, int height, int depth) {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.width = width;
		this.height = height;
		this.depth = depth;
	}

	public int getDepth() {
		return depth;
	}

	public int getHeight() {
		return height;
	}

	public int getSmallestPerimeter() {
		List<Integer> perimeters = List.of(width + width + height + height, height + height + depth + depth, depth + depth + width + width);
		return Collections.min(perimeters);
	}

	public int getSmallestSurfaceArea() {
		List<Integer> areas = List.of(width * height, height * depth, depth * width);
		return Collections.min(areas);
	}

	public int getSurfaceArea() {
		return 2 * (width * height + height * depth + depth * width);
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

	public int getZ() {
		return z;
	}

	public void growBackward() {
		this.z--;
		this.depth++;
	}

	public void growDown() {
		this.y--;
		this.height++;
	}

	public void growForward() {
		this.z++;
		this.depth++;
	}

	public void growLeft() {
		this.x--;
		this.width++;
	}

	public void growRight() {
		this.x++;
		this.width++;
	}

	public void growUp() {
		this.y++;
		this.height++;
	}
}
