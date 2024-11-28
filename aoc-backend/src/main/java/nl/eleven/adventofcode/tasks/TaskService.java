package nl.eleven.adventofcode.tasks;

import nl.eleven.adventofcode.puzzles.PuzzleService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TaskService {

	ApplicationContext context;

	PuzzleService puzzleService;

	public TaskService(ApplicationContext context, PuzzleService puzzleService) {
		this.context = context;
		this.puzzleService = puzzleService;
	}

	public String executeTask(int year, int day, int taskNumber) {
		TaskInterface<?> task = getTask(year, day, taskNumber);
		if (task == null) {
			return "Could not find task %d-%d-%d".formatted(year, day, taskNumber);
		}

		System.out.println("Reading puzzle input...");
		List<String> puzzleInput = puzzleService.read(year, day);

		System.out.printf("Executing task %d-%d-%d...", year, day, taskNumber);
		return task.executeTaskAndReturnString(taskNumber, puzzleInput);
	}

	TaskInterface<?> getTask(int year, int day, int taskNumber) {
		String beanName = "year" + year + "day" + day + "task" + taskNumber;

		if (!context.containsBean(beanName)) {
			beanName = "year" + year + "day" + day;
		}

		if (!context.containsBean(beanName)) {
			return null;
		}

		return (TaskInterface<?>) this.context.getBean(beanName);
	}

	public List<TaskDto> getAllTasks() {
		String[] result = context.getBeanNamesForType(TaskInterface.class);
		return Arrays
				.stream(result)
				.map(this::mapToTaskDto)
				.sorted((b, a) -> {
					if (a.year() == b.year()) {
						return a.day() - b.day();
					}
					return a.year() - b.year();
				})
				.toList();
	}

	private TaskDto mapToTaskDto(String beanName) {
		String[] split = beanName.split("day");
		int year = Integer.parseInt(split[0].substring(4));
		int day = Integer.parseInt(split[1].substring(0, 1));
		if (split[1].length() > 1) {
			day = Integer.parseInt(split[1].substring(0, 2));
		}

		String description = null;
		String url = "/rest/tasks/" + year + "/" + day + "/";
		String aocUrl = "https://adventofcode.com/" + year + "/day/" + day;
		return new TaskDto(beanName, description, year, day, url + "1", url + "2", aocUrl);
	}
}
