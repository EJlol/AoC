package nl.eleven.adventofcode.puzzles.year2022.day7_nospaceleftondevice;

public class FileStructure extends FileDirectoryStructure {

	// File variables
	private final int fileSize;

	public FileStructure(String name, DirectoryStructure parent, int fileSize) {
		super(name, parent);
		this.fileSize = fileSize;
	}

	@Override
	public int getSize() {
		return fileSize;
	}

	public boolean isDirectory() {
		return false;
	}
}
