package jaskell.util;

import java.util.Objects;

/**
 * @param <T>
 * @param <U>
 * @param <V>
 * @param <W>
 * @param <X>
 */
public class Tuple5<T, U, V, W, X>
        implements Tuple<T, Tuple4<U, V, W, X>, X, Tuple4<T, U, V, W>> {
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
        return functor.collect(getItem0(), getItem1(), getItem2(), getItem3(), getItem4());
    }

    public <Y> Tuple5<Y, U, V, W, X> item0(Y item) {
        return new Tuple5<>(item, getItem1(), getItem2(), getItem3(), getItem4());
    }

    public <Y> Tuple5<T, Y, V, W, X> item1(Y item) {
        return new Tuple5<>(getItem0(), item, getItem2(), getItem3(), getItem4());
    }

    public <Y> Tuple5<T, U, Y, W, X> item2(Y item) {
        return new Tuple5<>(getItem0(), getItem1(), item, getItem3(), getItem4());
    }

    public <Y> Tuple5<T, U, V, Y, X> item3(Y item) {
        return new Tuple5<>(getItem0(), getItem1(), getItem2(), item, getItem4());
    }

    public <Y> Tuple5<T, U, V, W, Y> item4(Y item) {
        return new Tuple5<>(getItem0(), getItem1(), getItem2(), getItem3(), item);
    }

    public <Y> Tuple6<T, U, V, W, X, Y> add(Y item) {
        return new Tuple6<>(getItem0(), getItem1(), getItem2(), getItem3(), getItem4(), item);
    }

    public <Y> Try<Tuple6<T, U, V, W, X, Y>> tryAdd(Try<Y> tryItem) {
        return tryItem.map(item -> new Tuple6<>(getItem0(), getItem1(), getItem2(), getItem3(), getItem4(), item));
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

    @Override
    public int size() {
        return 5;
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
            case 4:
                return getItem4();
            default:
                throw new IndexOutOfBoundsException("tuple5 only accept pos in range [0, 4]");
        }
    }

    @Override
    public T head() {
        return getItem0();
    }

    @Override
    public Tuple4<U, V, W, X> tail() {
        return new Tuple4<>(getItem1(), getItem2(), getItem3(), getItem4());
    }

    @Override
    public X last() {
        return getItem4();
    }

    @Override
    public Tuple4<T, U, V, W> butLast() {
        return new Tuple4<>(getItem0(), getItem1(), getItem2(), getItem3());
    }
}
