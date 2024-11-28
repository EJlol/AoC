package nl.eleven.adventofcode.puzzles.year2022.day7_nospaceleftondevice;

class FileStructure extends FileDirectoryStructure {

	// File variables
	private final int fileSize;

	FileStructure(String name, DirectoryStructure parent, int fileSize) {
		super(name, parent);
		this.fileSize = fileSize;
	}

	@Override
	int getSize() {
		return fileSize;
	}

	boolean isDirectory() {
		return false;
	}
}
