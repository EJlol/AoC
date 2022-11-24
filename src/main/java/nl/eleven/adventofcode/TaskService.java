package nl.eleven.adventofcode;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	ApplicationContext context;

	public TaskService(ApplicationContext context) {
		this.context = context;
	}

	Task getTask(int year, int day, int task) {
		String beanName = "year" + year + "day" + day + "task" + task;
		return (Task) this.context.getBean(beanName);
	}
}
