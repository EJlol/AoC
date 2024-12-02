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
class AdventOfCodeYear2023Test {

	@Autowired
	PuzzleService puzzleService;

	@Value("${sessionId}")
	String sessionId;

	@Autowired
	TaskService taskService;

	@Test
	void year2023day1() {
		executeTask(1, 1, "54968");
		executeTask(1, 2, "54094");
	}

	@Test
	void year2023day2() {
		executeTask(2, 1, "2716");
		executeTask(2, 2, "72227");
	}

	@Test
	void year2023day3() {
		executeTask(3, 1, "517021");
		executeTask(3, 2, "81296995");
	}

	@Test
	void year2023day4() {
		executeTask(4, 1, "517021");
		executeTask(4, 2, "81296995");
	}

	private void executeTask(int day, int taskNumber, String answer) {
		String result = taskService.executeTask(2023, day, taskNumber);
		assertEquals(answer, result);
	}
}
