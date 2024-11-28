package nl.eleven.adventofcode.puzzles.year2022.day7_nospaceleftondevice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectoryStructure extends FileDirectoryStructure {

	// Directory variables
	private final Map<String, FileDirectoryStructure> children = new HashMap<>();

	public DirectoryStructure(String name, DirectoryStructure parent) {
		super(name, parent);
	}

	public void addChild(FileDirectoryStructure file) {
		this.children.put(file.getName(), file);
	}

	public FileDirectoryStructure getChild(String filename) {
		return this.children.get(filename);
	}

	public List<DirectoryStructure> getChildDirectories() {
		return children.values().stream()
				.filter(FileDirectoryStructure::isDirectory)
				.map(DirectoryStructure.class::cast)
				.toList();
	}

	public Map<String, FileDirectoryStructure> getChildren() {
		return children;
	}

	@Override
	public int getSize() {
		return this.getChildren().values().stream().mapToInt(FileDirectoryStructure::getSize).sum();
	}

	public boolean hasChild(String directoryName) {
		return this.children.containsKey(directoryName);
	}

	public boolean isDirectory() {
		return true;
	}
}
