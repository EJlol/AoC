package nl.eleven.adventofcode.models.pair;

import java.util.function.BiFunction;

public class Pair<T1, T2> implements PairInterface<T1, T2> {

	private T1 left;

	private T2 right;

	public Pair(T1 left, T2 right) {
		this.left = left;
		this.right = right;
	}

	public T1 getLeft() {
		return left;
	}

	public void setLeft(T1 left) {
		this.left = left;
	}

	public T2 getRight() {
		return right;
	}

	public void setRight(T2 right) {
		this.right = right;
	}

	@Override
	public <R> PairInterface<R, T2> mapLeft(BiFunction<? super T1, ? super T2, ? extends R> mapper) {
		return new Pair<>(mapper.apply(getLeft(), getRight()), getRight());
	}

	@Override
	public <R> PairInterface<T1, R> mapRight(BiFunction<? super T1, ? super T2, ? extends R> mapper) {
		return new Pair<>(getLeft(), mapper.apply(getLeft(), getRight()));
	}
}
