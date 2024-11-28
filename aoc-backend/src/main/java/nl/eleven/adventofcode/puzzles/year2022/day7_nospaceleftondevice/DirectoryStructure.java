package nl.eleven.adventofcode.puzzles.year2022.day7_nospaceleftondevice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DirectoryStructure extends FileDirectoryStructure {

	// Directory variables
	private final Map<String, FileDirectoryStructure> children = new HashMap<>();

	DirectoryStructure(String name, DirectoryStructure parent) {
		super(name, parent);
	}

	void addChild(FileDirectoryStructure file) {
		this.children.put(file.getName(), file);
	}

	FileDirectoryStructure getChild(String filename) {
		return this.children.get(filename);
	}

	List<DirectoryStructure> getChildDirectories() {
		return children.values().stream()
				.filter(FileDirectoryStructure::isDirectory)
				.map(DirectoryStructure.class::cast)
				.toList();
	}

	Map<String, FileDirectoryStructure> getChildren() {
		return children;
	}

	@Override
	int getSize() {
		return this.getChildren().values().stream().mapToInt(FileDirectoryStructure::getSize).sum();
	}

	boolean hasChild(String directoryName) {
		return this.children.containsKey(directoryName);
	}

	boolean isDirectory() {
		return true;
	}
}
