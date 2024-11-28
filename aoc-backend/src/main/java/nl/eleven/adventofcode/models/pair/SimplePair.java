package nl.eleven.adventofcode.models.pair;

import java.util.function.Function;

public class SimplePair<T> extends Pair<T, T> {

	public SimplePair(T left, T right) {
		super(left, right);
	}

	public <T2> SimplePair<T2> map(Function<? super T, ? extends T2> mapper) {
		return new SimplePair<>(mapper.apply(getLeft()), mapper.apply(getRight()));
	}
}
