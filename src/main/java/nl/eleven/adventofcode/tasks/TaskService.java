package nl.eleven.adventofcode.tasks;

import nl.eleven.adventofcode.puzzles.PuzzleService;
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

	public String executeTask(int year, int day, int taskNumber) {
		Task task = getTask(year, day, taskNumber);
		if (task == null) {
			return "Could not find task " + year + " " + day + " " + taskNumber;
		}

		System.out.println("Reading puzzle input...");
		List<String> puzzleInput = puzzleService.read(year, day);

		System.out.println("Executing task...");
		return task.executeTaskAndReturnString(taskNumber, puzzleInput);
	}

	Task getTask(int year, int day, int taskNumber) {
		String beanName = "year" + year + "day" + day + "task" + taskNumber;

		if (!context.containsBean(beanName)) {
			beanName = "year" + year + "day" + day;
		}

		if (!context.containsBean(beanName)) {
			return null;
		}

		return (Task) this.context.getBean(beanName);
	}
}
