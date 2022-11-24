package nl.eleven.adventofcode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdventOfCodeController {

	TaskService taskService;

	public AdventOfCodeController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping(path = "/year/{year}/day/{day}/task/{taskNumber}")
	public int getTask(@PathVariable int year, @PathVariable int day, @PathVariable int taskNumber) {
		Task task = taskService.getTask(year, day, taskNumber);
		return task.executeTask();
	}
}
