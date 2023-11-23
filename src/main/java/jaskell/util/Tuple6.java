package jaskell.util;

import java.util.Objects;

public class Tuple6<T, U, V, W, X, Y> {
    final T item0;
    final U item1;
    final V item2;
    final W item3;
    final X item4;
    final Y item5;

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

    public Y getItem5() {
        return item5;
    }

    public Tuple6(T item0, U item1, V item2, W item3, X item4, Y item5) {
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.item5 = item5;
    }

    public <R> R uncurry(Function6<T, U, V, W, X, Y, R> functor) throws Exception {
        return functor.apply(getItem0(), getItem1(), getItem2(),
                getItem3(), getItem4(), getItem5());
    }

    public <R> Try<R> tryIt(Function6<T, U, V, W, X, Y, R> functor) {
        return functor.tryIt(getItem0(), getItem1(), getItem2(),
                getItem3(), getItem4(), getItem5());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple6)) return false;
        Tuple6<?, ?, ?, ?, ?, ?> tuple6 = (Tuple6<?, ?, ?, ?, ?, ?>) o;
        return Objects.equals(getItem0(), tuple6.getItem0()) && Objects.equals(getItem1(), tuple6.getItem1()) && Objects.equals(getItem2(), tuple6.getItem2()) && Objects.equals(getItem3(), tuple6.getItem3()) && Objects.equals(getItem4(), tuple6.getItem4()) && Objects.equals(getItem5(), tuple6.getItem5());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItem0(), getItem1(), getItem2(), getItem3(), getItem4(), getItem5());
    }

    public static <T, U, V, W, X, Y> Try<Tuple6<T, U, V, W, X, Y>> liftA(Try<T> t0, Try<U> t1,
                                                                            Try<V> t2, Try<W> t3,
                                                                            Try<X> t4, Try<Y> t5) {
        return Try.joinMap6(t0, t1, t2, t3, t4, t5, Tuple6::new);
    }
}
