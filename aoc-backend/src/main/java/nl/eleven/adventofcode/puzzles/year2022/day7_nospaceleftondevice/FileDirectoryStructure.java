package nl.eleven.adventofcode.puzzles.year2022.day7_nospaceleftondevice;

abstract class FileDirectoryStructure {

	private final String name;

	private final DirectoryStructure parent;

	FileDirectoryStructure(String name, DirectoryStructure parent) {
		this.name = name;
		this.parent = parent;
	}

	String getName() {
		return name;
	}

	DirectoryStructure getParent() {
		return parent;
	}

	abstract int getSize();

	abstract boolean isDirectory();
}
