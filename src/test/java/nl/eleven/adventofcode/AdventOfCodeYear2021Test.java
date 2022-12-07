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
class AdventOfCodeYear2021Test {

	@Autowired
	PuzzleService puzzleService;

	@Value("${sessionId}")
	String sessionId;

	@Autowired
	TaskService taskService;

	@Test
	void year2021day1() {
		executeTask(1, 1, "1184");
		executeTask(1, 2, "1158");
	}

	@Test
	void year2021day2() {
		executeTask(2, 1, "2039912");
		executeTask(2, 2, "1942068080");
	}

	@Test
	void year2021day3() {
		executeTask(3, 1, "2498354");
		executeTask(3, 2, "3277956");
	}

	private void executeTask(int day, int taskNumber, String answer) {
		String result = taskService.executeTask(2021, day, taskNumber);
		assertEquals(answer, result);
	}
}
