package jaskell.util;

import java.util.Objects;

public class Tuple6<T, U, V, W, X, Y>
        implements Tuple<T, Tuple5<U, V, W, X, Y>, Y, Tuple5<T, U, V, W, X>> {
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

    public <Z> Tuple6<Z, U, V, W, X, Y> item0(Z item) {
        return new Tuple6<>(item, getItem1(), getItem2(), getItem3(), getItem4(), getItem5());
    }

    public <Z> Tuple6<T, Z, V, W, X, Y> item1(Z item) {
        return new Tuple6<>(getItem0(), item, getItem2(), getItem3(), getItem4(), getItem5());
    }
    public <Z> Tuple6<T, U, Z, W, X, Y> item2(Z item) {
        return new Tuple6<>(getItem0(), getItem1(), item, getItem3(), getItem4(), getItem5());
    }
    public <Z> Tuple6<T, U, V, Z, X, Y> item3(Z item) {
        return new Tuple6<>(getItem0(), getItem1(), getItem2(), item, getItem4(), getItem5());
    }
    public <Z> Tuple6<T, U, V, W, Z, Y> item4(Z item) {
        return new Tuple6<>(getItem0(), getItem1(), getItem2(), getItem3(), item, getItem5());
    }
    public <Z> Tuple6<T, U, V, W, X, Z> item5(Z item) {
        return new Tuple6<>(getItem0(), getItem1(), getItem2(), getItem3(), getItem4(), item);
    }
    public <Z> Tuple7<T, U, V, W, X, Y, Z> add(Z item) {
        return new Tuple7<>(getItem0(), getItem1(), getItem2(), getItem3(), getItem4(), getItem5(), item);
    }
    public <Z> Try<Tuple7<T, U, V, W, X, Y, Z>> tryAdd(Try<Z> tryItem) {
        return tryItem.map(item -> new Tuple7<>(getItem0(), getItem1(), getItem2(),
                getItem3(), getItem4(), getItem5(), item));
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

    @Override
    public int size() {
        return 6;
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
            case 5:
                return getItem5();
            default:
                throw new IndexOutOfBoundsException("tuple6 only accept pos in range [0, 5]");
        }
    }

    @Override
    public T head() {
        return getItem0();
    }

    @Override
    public Tuple5<U, V, W, X, Y> tail() {
        return new Tuple5<>(getItem1(), getItem2(), getItem3(), getItem4(), getItem5());
    }

    @Override
    public Y last() {
        return getItem5();
    }

    @Override
    public Tuple5<T, U, V, W, X> butLast() {
        return new Tuple5<>(getItem0(), getItem1(), getItem2(), getItem3(), getItem4());
    }
}
