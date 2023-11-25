package jaskell.util;


import java.util.Objects;

/**
 * @param <T>
 * @param <U>
 */
public class Tuple2<T, U> {
    final T item0;
    final U item1;

    public Tuple2(T item0, U item1) {
        this.item0 = item0;
        this.item1 = item1;
    }

    public T getItem0() {
        return item0;
    }

    public U getItem1() {
        return item1;
    }

    public <V> Tuple2<V, U> item0(V item) {
        return new Tuple2<>(item, getItem1());
    }

    public <V> Tuple2<T, V> item1(V item) {
        return new Tuple2<>(getItem0(), item);
    }

    public <V> Tuple3<T, U, V> add(V item) {
        return new Tuple3<>(getItem0(), getItem1(), item);
    }

    public <V> Try<Tuple3<T, U, V>> tryAdd(Try<V> tryItem) {
        return tryItem.map(item -> new Tuple3<>(getItem0(), getItem1(), item));
    }

    public <R> R uncurry(BiFunction<T, U, R> functor) throws Exception {
        return functor.apply(getItem0(), getItem1());
    }

    public <R> Try<R> tryIt(BiFunction<T, U, R> functor) throws Exception {
        return functor.tryIt(getItem0(), getItem1());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple2)) return false;
        Tuple2<?, ?> tuple2 = (Tuple2<?, ?>) o;
        return Objects.equals(getItem0(), tuple2.getItem0()) && Objects.equals(getItem1(), tuple2.getItem1());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItem0(), getItem1());
    }

    public static <T, U> Try<Tuple2<T, U>> liftA(Try<T> t0, Try<U> t1) {
        return Try.joinMap(t0, t1, Tuple2::new);
    }

}
