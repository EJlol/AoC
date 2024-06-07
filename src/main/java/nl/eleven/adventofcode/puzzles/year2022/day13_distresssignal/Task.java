package nl.eleven.adventofcode.puzzles.year2022.day13_distresssignal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import nl.eleven.adventofcode.helpers.list.PartitionListBy;
import nl.eleven.adventofcode.models.pair.SimplePair;
import nl.eleven.adventofcode.tasks.TaskInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component("year2022day13")
public class Task implements TaskInterface<Integer> {

	@Override
	public Integer executeTask1(List<String> input) {
		final ObjectMapper mapper = new ObjectMapper();
		List<SimplePair<JsonNode>> pairs = PartitionListBy.inTwoByEmptyLine(input).stream()
				.map(pair -> pair.map(jsonString -> {
					try {
						return mapper.readTree(jsonString);
					} catch (JsonProcessingException e) {
						throw new RuntimeException(e);
					}
				})).toList();

		int sum = 0;
		for (int i = 1; i <= pairs.size(); i++) {
			if (compareJsonNode(pairs.get(i - 1).getLeft(), pairs.get(i - 1).getRight()).equals(CompareResult.TRUE)) {
				sum += i;
			}
		}
		return sum;
	}

	@Override
	public Integer executeTask2(List<String> input) {
		final ObjectMapper mapper = new ObjectMapper();
		List<SimplePair<JsonNode>> pairs = PartitionListBy.inTwoByEmptyLine(input).stream()
				.map(pair -> pair.map(jsonString -> {
					try {
						return mapper.readTree(jsonString);
					} catch (JsonProcessingException e) {
						throw new RuntimeException(e);
					}
				})).toList();

		List<JsonNode> values = new ArrayList<>(pairs.stream()
				.flatMap(p -> Stream.of(p.getLeft(), p.getRight()))
				.toList());
		values.forEach(System.out::println);
		System.out.println();
		try {
			JsonNode two = mapper.readTree("[[2]]");
			JsonNode six = mapper.readTree("[[6]]");
			values.add(two);
			values.add(six);
			values.sort((o1, o2) -> {
				System.out.println(o1.toString() + " vs " + o2.toString() + " = " + compareJsonNode(o1, o2).name());
				return compareJsonNode(o1, o2).getValue();
			});
			return (values.indexOf(two) + 1) * (values.indexOf(six) + 1);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	private CompareResult compareArrays(ArrayNode left, ArrayNode right) {

		ArrayNode leftClone = left.deepCopy();
		ArrayNode rightClone = right.deepCopy();
		CompareResult result = CompareResult.EQUAL;
		while (result.equals(CompareResult.EQUAL) && !leftClone.isEmpty() && !rightClone.isEmpty()) {
			JsonNode leftValue = leftClone.remove(0);
			JsonNode rightValue = rightClone.remove(0);
			result = compareJsonNode(leftValue, rightValue);
		}

		if (result.equals(CompareResult.EQUAL) && leftClone.isEmpty() && !rightClone.isEmpty()) {
			return CompareResult.TRUE;
		} else if (result.equals(CompareResult.EQUAL) && rightClone.isEmpty() && !leftClone.isEmpty()) {
			return CompareResult.FALSE;
		}

		return result;
	}

	private CompareResult compareJsonNode(JsonNode left, JsonNode right) {
		if (left.toString().equals(right.toString())) {
			return CompareResult.EQUAL;
		}
		if (left.isInt() && right.isInt()) {
			return CompareResult.compare(left.asInt(), right.asInt());
		} else if (left.isArray() && right.isArray()) {
			return compareArrays((ArrayNode) left, (ArrayNode) right);
		} else {
			ObjectMapper mapper = new ObjectMapper();
			ArrayNode leftArray = left.isArray() ? (ArrayNode) left : mapper.createArrayNode().add(left);
			ArrayNode rightArray = right.isArray() ? (ArrayNode) right : mapper.createArrayNode().add(right);
			return compareJsonNode(leftArray, rightArray);
		}
	}
}
