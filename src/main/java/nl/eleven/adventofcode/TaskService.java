package nl.eleven.adventofcode;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

	ApplicationContext context;

	PuzzleService puzzleService;

	public TaskService(ApplicationContext context, PuzzleService puzzleService) {
		this.context = context;
		this.puzzleService = puzzleService;
	}

	int executeTask(int year, int day, int taskNumber) {
		Task task = getTask(year, day, taskNumber);
		if (task == null) {
			return 0;
		}

		List<String> puzzleInput = puzzleService.read(year, day);
		return task.executeTask(puzzleInput);
	}

	Task getTask(int year, int day, int taskNumber) {
		String beanName = "year" + year + "day" + day + "task" + taskNumber;

		if (!context.containsBean(beanName)) {
			return null;
		}

		return (Task) this.context.getBean(beanName);
	}
}
