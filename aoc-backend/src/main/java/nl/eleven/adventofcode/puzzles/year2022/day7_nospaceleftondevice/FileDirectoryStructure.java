package nl.eleven.adventofcode.puzzles.year2022.day7_nospaceleftondevice;

public abstract class FileDirectoryStructure {

	private final String name;

	private final DirectoryStructure parent;

	protected FileDirectoryStructure(String name, DirectoryStructure parent) {
		this.name = name;
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public DirectoryStructure getParent() {
		return parent;
	}

	public abstract int getSize();

	public abstract boolean isDirectory();
}
