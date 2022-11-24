package nl.eleven.adventofcode.readers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class InstructionReader<T> implements PuzzleReader {

	public Stream<T> read(String url, Class<T> clazz) {
		String body = PuzzleReader.getPuzzleContent(url);

		try {
			Constructor<T> constructor = clazz.getConstructor(String.class);
			return body.lines().map(line -> {
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
