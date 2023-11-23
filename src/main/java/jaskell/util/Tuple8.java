package jaskell.util;


import java.util.Objects;

public class Tuple8<S, T, U, V, W, X, Y, Z> {
    final S item0;
    final T item1;
    final U item2;
    final V item3;
    final W item4;
    final X item5;
    final Y item6;
    final Z item7;

    public S getItem0() {
        return item0;
    }

    public T getItem1() {
        return item1;
    }

    public U getItem2() {
        return item2;
    }

    public V getItem3() {
        return item3;
    }

    public W getItem4() {
        return item4;
    }

    public X getItem5() {
        return item5;
    }

    public Y getItem6() {
        return item6;
    }

    public Z getItem7() {
        return item7;
    }

    public Tuple8(S item0, T item1, U item2, V item3, W item4, X item5, Y item6, Z item7) {
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.item5 = item5;
        this.item6 = item6;
        this.item7 = item7;
    }

    public <R> R uncurry(Function8<S, T, U, V, W, X, Y, Z, R> functor) throws Exception {
        return functor.apply(getItem0(), getItem1(), getItem2(),
                getItem3(), getItem4(), getItem5(),
                getItem6(), getItem7());
    }

    public <R> Try<R> tryIt(Function8<S, T, U, V, W, X, Y, Z, R> functor) throws Exception {
        return functor.tryIt(getItem0(), getItem1(), getItem2(),
                getItem3(), getItem4(), getItem5(),
                getItem6(), getItem7());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple8)) return false;
        Tuple8<?, ?, ?, ?, ?, ?, ?, ?> tuple8 = (Tuple8<?, ?, ?, ?, ?, ?, ?, ?>) o;
        return Objects.equals(getItem0(), tuple8.getItem0()) && Objects.equals(getItem1(), tuple8.getItem1()) && Objects.equals(getItem2(), tuple8.getItem2()) && Objects.equals(getItem3(), tuple8.getItem3()) && Objects.equals(getItem4(), tuple8.getItem4()) && Objects.equals(getItem5(), tuple8.getItem5()) && Objects.equals(getItem6(), tuple8.getItem6()) && Objects.equals(getItem7(), tuple8.getItem7());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItem0(), getItem1(), getItem2(), getItem3(), getItem4(), getItem5(), getItem6(), getItem7());
    }

    public static <S, T, U, V, W, X, Y, Z> Try<Tuple8<S, T, U, V, W, X, Y, Z>> liftA(
            Try<S> t0, Try<T> t1, Try<U> t2, Try<V> t3, Try<W> t4, Try<X> t5, Try<Y> t6, Try<Z> t7) {
        return Try.joinMap8(t0, t1, t2, t3, t4, t5, t6, t7, Tuple8::new);
    }
}