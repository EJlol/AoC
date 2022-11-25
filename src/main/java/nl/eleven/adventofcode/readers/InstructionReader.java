package nl.eleven.adventofcode.readers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class InstructionReader<T> {

	public Stream<T> parse(Stream<String> stream, Class<T> clazz) {
		try {
			Constructor<T> constructor = clazz.getConstructor(String.class);
			return stream.map(line -> {
				try {
					return constructor.newInstance(line);
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			});
		} catch (RuntimeException | NoSuchMethodException e) {
			return Stream.empty();
		}
	}
}
