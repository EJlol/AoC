package nl.eleven.adventofcode.table;

import nl.eleven.adventofcode.table.utils.RotateTable;

import java.util.List;
import java.util.function.Predicate;

public record Table<T>(List<List<T>> contents) {

	public int getHeight() {
		return this.contents.size();
	}

	public int getWidth() {
		return !this.contents.isEmpty() ? this.contents.get(0).size() : 0;
	}

	public Table<T> rotate() {
		return new Table<>(RotateTable.rotate(this.contents));
	}

	public Table<T> filterRows(Predicate<? super List<T>> predicate) {
		return new Table<>(this.contents.stream().filter(predicate).toList());
	}

	public List<T> getRow(int index) {
		return this.contents.get(index);
	}

}
