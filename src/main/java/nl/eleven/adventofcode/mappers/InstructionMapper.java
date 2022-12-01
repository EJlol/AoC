package nl.eleven.adventofcode.mappers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class InstructionMapper<T> {

	public List<T> map(List<String> input, Class<T> clazz) {
		try {
			Constructor<T> constructor = clazz.getConstructor(String.class);
			return input.stream().map(line -> {
				try {
					return constructor.newInstance(line);
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}).toList();
		} catch (RuntimeException | NoSuchMethodException e) {
			return List.of();
		}
	}
}
