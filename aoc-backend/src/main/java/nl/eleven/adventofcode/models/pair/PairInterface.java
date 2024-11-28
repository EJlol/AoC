package nl.eleven.adventofcode.models.pair;

import java.util.function.BiFunction;

public interface PairInterface<T1, T2> {

	T1 getLeft();

	T2 getRight();

	<R> PairInterface<R, T2> mapLeft(BiFunction<? super T1, ? super T2, ? extends R> mapper);

	<R> PairInterface<T1, R> mapRight(BiFunction<? super T1, ? super T2, ? extends R> mapper);
}
