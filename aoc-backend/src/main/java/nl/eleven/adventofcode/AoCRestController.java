package nl.eleven.adventofcode;

import nl.eleven.adventofcode.tasks.TaskDto;
import nl.eleven.adventofcode.tasks.TaskService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/tasks")
public class AoCRestController {

	TaskService taskService;

	public AoCRestController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping(path = "/{year}/{day}/{taskNumber}")
	public String getTask(@PathVariable int year, @PathVariable int day, @PathVariable int taskNumber) {
		return taskService.executeTask(year, day, taskNumber);
	}

	@GetMapping(path = "/")
	public List<TaskDto> index(Model model) {
		return taskService.getAllTasks();
	}
}
