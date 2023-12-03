package jaskell.util;


import java.util.Objects;

public class Tuple7<T, U, V, W, X, Y, Z>
        implements Tuple<T, Tuple6<U, V, W, X, Y, Z>, Z, Tuple6<T, U, V, W, X, Y>> {
    final T item0;
    final U item1;
    final V item2;
    final W item3;
    final X item4;
    final Y item5;
    final Z item6;

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

    public Z getItem6() {
        return item6;
    }

    public Tuple7(T item0, U item1, V item2, W item3, X item4, Y item5, Z item6) {
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.item5 = item5;
        this.item6 = item6;
    }

    public <R> R uncurry(Function7<T, U, V, W, X, Y, Z, R> functor) throws Exception {
        return functor.apply(getItem0(), getItem1(), getItem2(),
                getItem3(), getItem4(), getItem5(), getItem6());
    }

    public <R> Try<R> tryIt(Function7<T, U, V, W, X, Y, Z, R> functor) throws Exception {
        return functor.collect(getItem0(), getItem1(), getItem2(),
                getItem3(), getItem4(), getItem5(), getItem6());
    }

    public <A> Tuple7<A, U, V, W, X, Y, Z> item0(A item) {
        return new Tuple7<>(item, getItem1(), getItem2(), getItem3(), getItem4(), getItem5(), getItem6());
    }

    public <A> Tuple7<T, A, V, W, X, Y, Z> item1(A item) {
        return new Tuple7<>(getItem0(), item, getItem2(), getItem3(), getItem4(), getItem5(), getItem6());
    }

    public <A> Tuple7<T, U, A, W, X, Y, Z> item2(A item) {
        return new Tuple7<>(getItem0(), getItem1(), item, getItem3(), getItem4(), getItem5(), getItem6());
    }

    public <A> Tuple7<T, U, V, A, X, Y, Z> item3(A item) {
        return new Tuple7<>(getItem0(), getItem1(), getItem2(), item, getItem4(), getItem5(), getItem6());
    }

    public <A> Tuple7<T, U, V, W, A, Y, Z> item4(A item) {
        return new Tuple7<>(getItem0(), getItem1(), getItem2(), getItem3(), item, getItem5(), getItem6());
    }

    public <A> Tuple7<T, U, V, W, X, A, Z> item5(A item) {
        return new Tuple7<>(getItem0(), getItem1(), getItem2(), getItem3(), getItem4(), item, getItem6());
    }

    public <A> Tuple7<T, U, V, W, X, Y, A> item6(A item) {
        return new Tuple7<>(getItem0(), getItem1(), getItem2(), getItem3(), getItem4(), getItem5(), item);
    }

    public <A> Tuple8<T, U, V, W, X, Y, Z, A> add(A item) {
        return new Tuple8<>(getItem0(), getItem1(), getItem2(), getItem3(), getItem4(), getItem5(), getItem6(), item);
    }

    public <A> Try<Tuple8<T, U, V, W, X, Y, Z, A>> tryAdd(Try<A> tryItem) {
        return tryItem.map(item -> new Tuple8<>(getItem0(), getItem1(), getItem2(), getItem3(),
                getItem4(), getItem5(), getItem6(), item));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple7)) return false;
        Tuple7<?, ?, ?, ?, ?, ?, ?> tuple7 = (Tuple7<?, ?, ?, ?, ?, ?, ?>) o;
        return Objects.equals(getItem0(), tuple7.getItem0()) && Objects.equals(getItem1(), tuple7.getItem1()) && Objects.equals(getItem2(), tuple7.getItem2()) && Objects.equals(getItem3(), tuple7.getItem3()) && Objects.equals(getItem4(), tuple7.getItem4()) && Objects.equals(getItem5(), tuple7.getItem5()) && Objects.equals(getItem6(), tuple7.getItem6());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItem0(), getItem1(), getItem2(), getItem3(), getItem4(), getItem5(), getItem6());
    }

    public static <T, U, V, W, X, Y, Z> Try<Tuple7<T, U, V, W, X, Y, Z>> liftA(
            Try<T> t0, Try<U> t1, Try<V> t2, Try<W> t3, Try<X> t4, Try<Y> t5, Try<Z> t6) {
        return Try.joinMap7(t0, t1, t2, t3, t4, t5, t6, Tuple7::new);
    }

    @Override
    public int size() {
        return 7;
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
            case 6:
                return getItem6();
            default:
                throw new IndexOutOfBoundsException("tuple7 only accept pos in range [0, 6]");
        }
    }

    @Override
    public T head() {
        return getItem0();
    }

    @Override
    public Tuple6<U, V, W, X, Y, Z> tail() {
        return new Tuple6<>(getItem1(), getItem2(), getItem3(), getItem4(), getItem5(), getItem6());
    }

    @Override
    public Z last() {
        return getItem6();
    }

    @Override
    public Tuple6<T, U, V, W, X, Y> butLast() {
        return new Tuple6<>(getItem0(), getItem1(), getItem2(), getItem3(), getItem4(), getItem5());
    }
}
