package nl.eleven.adventofcode;

import nl.eleven.adventofcode.puzzles.PuzzleService;
import nl.eleven.adventofcode.tasks.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("secret")
class AdventOfCodeYear2015Test {

	@Autowired
	PuzzleService puzzleService;

	@Value("${sessionId}")
	String sessionId;

	@Autowired
	TaskService taskService;

	@Test
	void year2015day2() {
		executeTask(2, 1, "1588178");
		executeTask(2, 2, "3783758");
	}

	@Test
	void year2015day3() {
		executeTask(3, 1, "2592");
		executeTask(3, 2, "2360");
	}

	@Test
	void year2015day4() {
		executeTask(4, 1, "346386");
		executeTask(4, 2, "9958218");
	}

	@Test
	void year2015day5() {
		executeTask(5, 1, "238");
		executeTask(5, 2, "69");
	}

	private void executeTask(int day, int taskNumber, String answer) {
		String result = taskService.executeTask(2015, day, taskNumber);
		assertEquals(answer, result);
	}
}
