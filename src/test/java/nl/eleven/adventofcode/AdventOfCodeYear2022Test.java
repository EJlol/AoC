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
class AdventOfCodeYear2022Test {

	@Autowired
	PuzzleService puzzleService;

	@Value("${sessionId}")
	String sessionId;

	@Autowired
	TaskService taskService;

	@Test
	void year2022day1() {
		executeTask(1, 1, "71502");
		executeTask(1, 2, "208191");
	}

	@Test
	void year2022day2() {
		executeTask(2, 1, "12772");
		executeTask(2, 2, "11618");
	}

	@Test
	void year2022day3() {
		executeTask(3, 1, "7872");
		executeTask(3, 2, "2497");
	}

	@Test
	void year2022day4() {
		executeTask(4, 1, "571");
		executeTask(4, 2, "917");
	}

	private void executeTask(int day, int taskNumber, String answer) {
		String result = taskService.executeTask(2022, day, taskNumber);
		assertEquals(answer, result);
	}
}
