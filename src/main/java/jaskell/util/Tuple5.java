package jaskell.util;

import java.util.Objects;

/**
 * @param <T>
 * @param <U>
 * @param <V>
 * @param <W>
 * @param <X>
 */
public class Tuple5<T, U, V, W, X> {
    final T item0;
    final U item1;
    final V item2;
    final W item3;
    final X item4;

    public T getItem0() {
        return item0;
    }

    public U getItem1() {
        return item1;
    }

    public V getItem2() {
        return item2;
    }

    public W getItem3() {
        return item3;
    }

    public X getItem4() {
        return item4;
    }

    public Tuple5(T item0, U item1, V item2, W item3, X item4) {
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
    }

    public <R> R uncurry(Function5<T, U, V, W, X, R> functor) throws Exception {
        return functor.apply(getItem0(), getItem1(), getItem2(), getItem3(), getItem4());
    }

    public <R> Try<R> tryIt(Function5<T, U, V, W, X, R> functor) {
        return functor.tryIt(getItem0(), getItem1(), getItem2(), getItem3(), getItem4());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple5)) return false;
        Tuple5<?, ?, ?, ?, ?> tuple5 = (Tuple5<?, ?, ?, ?, ?>) o;
        return Objects.equals(getItem0(), tuple5.getItem0()) && Objects.equals(getItem1(), tuple5.getItem1()) && Objects.equals(getItem2(), tuple5.getItem2()) && Objects.equals(getItem3(), tuple5.getItem3()) && Objects.equals(getItem4(), tuple5.getItem4());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItem0(), getItem1(), getItem2(), getItem3(), getItem4());
    }

    public static <T, U, V, W, X> Try<Tuple5<T, U, V, W, X>> liftA(Try<T> t0, Try<U> t1, Try<V> t2,
                                                                      Try<W> t3, Try<X> t4) {
        return Try.joinMap5(t0, t1, t2, t3, t4, Tuple5::new);
    }
}
