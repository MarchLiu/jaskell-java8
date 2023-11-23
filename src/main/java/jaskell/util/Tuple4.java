package jaskell.util;


import java.util.Objects;

public class Tuple4<T, U, V, W> {
    final T item0;
    final U item1;
    final V item2;
    final W item3;

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

    public Tuple4(T item0, U item1, V item2, W item3) {
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }

    public <R> R uncurry(Function4<T, U, V, W, R> functor) throws Exception {
        return functor.apply(getItem0(), getItem1(), getItem2(), getItem3());
    }

    public <R> Try<R> tryIt(Function4<T, U, V, W, R> functor) throws Exception {
        return functor.tryIt(getItem0(), getItem1(), getItem2(), getItem3());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple4)) return false;
        Tuple4<?, ?, ?, ?> tuple4 = (Tuple4<?, ?, ?, ?>) o;
        return Objects.equals(getItem0(), tuple4.getItem0()) && Objects.equals(getItem1(), tuple4.getItem1()) && Objects.equals(getItem2(), tuple4.getItem2()) && Objects.equals(getItem3(), tuple4.getItem3());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItem0(), getItem1(), getItem2(), getItem3());
    }

    public static <T, U, V, W> Try<Tuple4<T, U, V, W>> liftA(Try<T> t0, Try<U> t1, Try<V> t2, Try<W> t3) {
        return Try.joinMap4(t0, t1, t2, t3, Tuple4::new);
    }
}