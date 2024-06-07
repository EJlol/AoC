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

	@Test
	void year2022day5() {
		executeTask(5, 1, "VCTFTJQCG");
		executeTask(5, 2, "GCFGLDNJZ");
	}

	@Test
	void year2022day6() {
		executeTask(6, 1, "1566");
		executeTask(6, 2, "2265");
	}

	@Test
	void year2022day7() {
		executeTask(7, 1, "1306611");
		executeTask(7, 2, "13210366");
	}

	@Test
	void year2022day8() {
		executeTask(8, 1, "1708");
		executeTask(8, 2, "504000");
	}

	@Test
	void year2022day9() {
		executeTask(9, 1, "6023");
		executeTask(9, 2, "2533");
	}

	@Test
	void year2022day10() {
		executeTask(10, 1, "15140");
		executeTask(10, 2,
				"""
						###..###....##..##..####..##...##..###.#<br>
						#..#.#..#....#.#..#....#.#..#.#..#.#..##<br>
						###..#..#....#.#..#...#..#....#..#.#..#.<br>
						#..#.###.....#.####..#...#.##.####.###..<br>
						#..#.#....#..#.#..#.#....#..#.#..#.#....<br>
						###..#.....##..#..#.####..###.#..#.#....<br>
						""");
	}

	@Test
	void year2022day11() {
		executeTask(11, 1, "111210");
		executeTask(11, 2, "15447387620");
	}

	@Test
	void year2022day12() {
		executeTask(12, 1, "462");
		executeTask(12, 2, "451");
	}

	@Test
	void year2022day13() {
		executeTask(13, 1, "6428");
		executeTask(13, 2, "22464");
	}

	@Test
	void year2022day14() {
		executeTask(14, 1, "793");
		executeTask(14, 2, "24166");
	}

	@Test
	void year2022day15() {
		executeTask(15, 1, "4985193");
		executeTask(15, 2, "11583882601918");
	}

	@Test
	void year2022day18() {
		executeTask(18, 1, "3470");
		executeTask(18, 2, "1986");
	}

	private void executeTask(int day, int taskNumber, String answer) {
		String result = taskService.executeTask(2022, day, taskNumber);
		assertEquals(answer, result);
	}
}
