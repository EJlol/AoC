package nl.eleven.adventofcode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("secret")
class AdventOfCodeApplicationTests {

	@Autowired
	PuzzleService puzzleService;

	@Value("${sessionId}")
	String sessionId;

	@Autowired
	TaskService taskService;

	@Test
	void year2021day1() {
		executeTask(2021, 1, 1, 1184);
		executeTask(2021, 1, 2, 1158);
	}

	@Test
	void year2021day2() {
		executeTask(2021, 2, 1, 2039912);
		executeTask(2021, 2, 2, 1942068080);
	}

	@Test
	void year2021day3() {
		executeTask(2021, 3, 1, 2498354);
		executeTask(2021, 3, 2, 3277956);
	}

	@Test
	void year2022day1() {
		executeTask(2022, 1, 1, 71502);
		executeTask(2022, 1, 2, 208191);
	}

	@Test
	void year2022day2() {
		executeTask(2022, 2, 1, 12772);
		executeTask(2022, 2, 2, 11618);
	}

	private void executeTask(int year, int day, int taskNumber, int answer) {
		int result = taskService.executeTask(year, day, taskNumber);
		assertEquals(answer, result);
	}
}
