package nl.eleven.adventofcode.puzzles.year2022.day7_nospaceleftondevice;

import nl.eleven.adventofcode.helpers.list.PartitionListBy;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("year2022day7")
public class Task implements TaskInterface<Integer> {

	DirectoryStructure root = new DirectoryStructure("/", null);

	DirectoryStructure currentDirectory = root;

	@Override
	public Integer executeTask1(List<String> input) {
		List<DirectoryStructure> allDirectories = parseFileStructure(input);

		return allDirectories.stream()
				.filter(c -> c.getSize() < 100_000)
				.mapToInt(FileDirectoryStructure::getSize).sum();
	}

	@Override
	public Integer executeTask2(List<String> input) {
		List<DirectoryStructure> allDirectories = parseFileStructure(input);

		int totalDiskSpace = 70_000_000;
		int requiredSpace = 30_000_000;
		int usedSpace = root.getSize();
		int toFree = requiredSpace - (totalDiskSpace - usedSpace);

		return allDirectories.stream()
				.filter(c -> c.getSize() > toFree)
				.mapToInt(FileDirectoryStructure::getSize).min().orElse(0);
	}

	private void changeDirectory(String[] params) {
		String directoryName = params[2];
		if (directoryName.equals("/")) {
			currentDirectory = root;
		} else if (directoryName.equals("..")) {
			currentDirectory = currentDirectory.getParent();
		} else {
			if (!currentDirectory.hasChild(directoryName)) {
				currentDirectory.addChild(new DirectoryStructure(directoryName, currentDirectory));
			}
			currentDirectory = (DirectoryStructure) currentDirectory.getChild(directoryName);
		}
	}

	private void getAllDirectories(List<DirectoryStructure> directories, DirectoryStructure directory) {
		List<DirectoryStructure> childDirectories = directory.getChildDirectories();
		directories.addAll(childDirectories);
		childDirectories.forEach(d -> getAllDirectories(directories, d));
	}

	private void list(List<String> terminalBlockText) {
		terminalBlockText.removeFirst();
		terminalBlockText.forEach(line -> {
			if (line.startsWith("dir ")) {
				currentDirectory.addChild(new DirectoryStructure(line.substring(4), currentDirectory));
			} else {
				String[] fileAttributes = line.split(" ");
				int size = Integer.parseInt(fileAttributes[0]);
				currentDirectory.addChild(new FileStructure(fileAttributes[1], currentDirectory, size));
			}
		});
	}

	private List<DirectoryStructure> parseFileStructure(List<String> input) {
		List<List<String>> commands = PartitionListBy.startsWith(input, "$");
		commands.forEach(terminalBlockText -> {
			if (terminalBlockText.isEmpty()) {
				return;
			}

			String[] params = terminalBlockText.getFirst().split(" ");
			String command = params[1];
			if (command.equals("cd")) {
				changeDirectory(params);
			} else {
				// command is ls
				list(terminalBlockText);
			}
		});

		List<DirectoryStructure> allDirectories = new ArrayList<>();
		getAllDirectories(allDirectories, root);

		return allDirectories;
	}
}
