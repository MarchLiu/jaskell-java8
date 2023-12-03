package jaskell.util;


import java.util.Objects;

/**
 * @param <T>
 * @param <U>
 * @param <V>
 */
public class Tuple3<T, U, V> implements Tuple<T, Tuple2<U, V>, U, Tuple2<T, U>>  {
    final T item0;
    final U item1;
    final V item2;

    public Tuple3(T item0, U item1, V item2) {
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
    }

    public T getItem0() {
        return item0;
    }

    public U getItem1() {
        return item1;
    }

    public V getItem2() {
        return item2;
    }



    public <R> R uncurry(TriFunction<T, U, V, R> functor) throws Exception {
        return functor.apply(getItem0(), getItem1(), getItem2());
    }

    public <R> Try<R> tryIt(TriFunction<T, U, V, R> functor) throws Exception {
        return functor.collect(getItem0(), getItem1(), getItem2());
    }

    public <W> Tuple3<W, U, V> item0(W item) {
        return new Tuple3<>(item, getItem1(), getItem2());
    }

    public <W> Tuple3<T, W, V> item1(W item) {
        return new Tuple3<>(getItem0(), item, getItem2());
    }

    public <W> Tuple3<T, U, W> item2(W item) {
        return new Tuple3<>(getItem0(), getItem1(), item);
    }

    public <W> Tuple4<T, U, V, W> add(W item) {
        return new Tuple4<>(getItem0(), getItem1(), getItem2(), item);
    }

    public <W> Try<Tuple4<T, U, V, W>> tryAdd(Try<W> tryItem) {
        return tryItem.map(item -> new Tuple4<>(getItem0(), getItem1(), getItem2(), item));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple3)) return false;
        Tuple3<?, ?, ?> tuple3 = (Tuple3<?, ?, ?>) o;
        return Objects.equals(getItem0(), tuple3.getItem0()) && Objects.equals(getItem1(), tuple3.getItem1()) && Objects.equals(getItem2(), tuple3.getItem2());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItem0(), getItem1(), getItem2());
    }

    public static <T, U, V> Try<Tuple3<T, U, V>> liftA(Try<T> t0, Try<U> t1, Try<V> t2) {
        return Try.joinMap3(t0, t1, t2, Tuple3::new);
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public Object get(int pos) throws IndexOutOfBoundsException {
        switch (pos) {
            case 0:
                return getItem0();
            case 1:
                return getItem1();
            case 2:
                return getItem2();
            default:
                throw new IndexOutOfBoundsException("tuple3 only accept pos in range [0, 2]");
        }
    }

    @Override
    public T head() {
        return getItem0();
    }

    @Override
    public Tuple2<U, V> tail() {
        return new Tuple2<>(getItem1(), getItem2());
    }

    @Override
    public U last() {
        return getItem1();
    }

    @Override
    public Tuple2<T, U> butLast() {
        return new Tuple2<>(getItem0(), getItem1());
    }
}
