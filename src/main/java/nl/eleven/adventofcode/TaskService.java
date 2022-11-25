package nl.eleven.adventofcode;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class TaskService {

	ApplicationContext context;

	public TaskService(ApplicationContext context) {
		this.context = context;
	}

	int executeTask(int year, int day, int taskNumber) {
		Task task = getTask(year, day, taskNumber);
		if (task == null) {
			return 0;
		}

		String inputUrl = "https://adventofcode.com/" + year + "/day/" + day + "/input";
		String session = "53616c7465645f5f72378ff1d4d376c4d5dbc709d0eb6c0889b1ffe277795ab37a613ac8552511b5ef1630bb1935fbf94936ef54f2ceafd63ce156bfa8d9209c";

		Stream<String> stream = PuzzleReader.read(inputUrl, session);
		return task.executeTask(stream);
	}

	Task getTask(int year, int day, int taskNumber) {
		String beanName = "year" + year + "day" + day + "task" + taskNumber;

		if (!context.containsBean(beanName)) {
			return null;
		}

		return (Task) this.context.getBean(beanName);
	}
}
