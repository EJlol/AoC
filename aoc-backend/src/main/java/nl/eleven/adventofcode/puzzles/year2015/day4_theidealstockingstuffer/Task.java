package nl.eleven.adventofcode.puzzles.year2015.day4_theidealstockingstuffer;

import com.google.common.base.Strings;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("year2015day4")
public class Task implements TaskInterface<Integer> {

	@Override
	public Integer executeTask1(List<String> input) {
		return findAmountOfHashesNeeded(input.getFirst(), 5);
	}

	@Override
	public Integer executeTask2(List<String> input) {
		return findAmountOfHashesNeeded(input.getFirst(), 6);
	}

	private int findAmountOfHashesNeeded(String startsWith, int amountOfZeros) {
		String zeros = Strings.repeat("0", amountOfZeros);
		String hash = "";
		int n = 0;
		while (!hash.startsWith(zeros)) {
			n++;
			hash = DigestUtils.md5Hex(startsWith + n);
		}
		return n;
	}
}
