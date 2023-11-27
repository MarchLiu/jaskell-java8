package jaskell.util;


import java.util.Objects;

public class Tuple4<T, U, V, W> implements Tuple<T, Tuple3<U, V, W>, W, Tuple3<T, U, V>> {
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

    public <X> Tuple4<X, U, V, W> item0(X item) {
        return new Tuple4<>(item, getItem1(), getItem2(), getItem3());
    }

    public <X> Tuple4<T, X, V, W> item1(X item) {
        return new Tuple4<>(getItem0(), item, getItem2(), getItem3());
    }

    public <X> Tuple4<T, U, X, W> item2(X item) {
        return new Tuple4<>(getItem0(), getItem1(), item, getItem3());
    }

    public <X> Tuple4<T, U, V, X> item3(X item) {
        return new Tuple4<>(getItem0(), getItem1(), getItem2(), item);
    }

    public <X> Tuple5<T, U, V, W, X> add(X item) {
        return new Tuple5<>(getItem0(), getItem1(), getItem2(), getItem3(), item);
    }

    public <X> Try<Tuple5<T, U, V, W, X>> tryAdd(Try<X> tryItem) {
        return tryItem.map(item -> new Tuple5<>(getItem0(), getItem1(), getItem2(), getItem3(), item));
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

    @Override
    public int size() {
        return 4;
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
            case 3:
                return getItem3();
            default:
                throw new IndexOutOfBoundsException("tuple4 only accept pos in range [0, 3]");
        }
    }

    @Override
    public T head() {
        return getItem0();
    }

    @Override
    public Tuple3<U, V, W> tail() {
        return new Tuple3<>(getItem1(), getItem2(), getItem3());
    }

    @Override
    public W last() {
        return getItem3();
    }

    @Override
    public Tuple3<T, U, V> butLast() {
        return new Tuple3<>(getItem0(), getItem1(), getItem2());
    }
}
