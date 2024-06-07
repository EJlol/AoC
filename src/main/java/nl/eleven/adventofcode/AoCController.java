package nl.eleven.adventofcode;

import nl.eleven.adventofcode.tasks.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AoCController {

	TaskService taskService;

	public AoCController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping(path = "/")
	public String index(Model model) {
		model.addAttribute("tasks", taskService.getAllTasks());
		return "index";
	}
}
