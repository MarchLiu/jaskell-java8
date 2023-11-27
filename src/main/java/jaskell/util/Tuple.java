package jaskell.util;

import java.util.*;

public interface Tuple<Head, Tail, Last, ButLast> {
    int size();

    Head head();
    Tail tail();

    Last last();
    ButLast butLast();

    Object get(int pos) throws IndexOutOfBoundsException;

    default List<Object> toList() {
        List<Object> result = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            result.add(get(i));
        }
        return result;
    }

    default Map<String, Object> toMap(String... keys) throws IllegalArgumentException {
        if (keys.length != size()) {
            throw new IllegalArgumentException(
                    String.format("tuple%d has %d fields but %d keys given %s",
                            size(), size(), keys.length, Arrays.asList(keys)));
        }
        Map<String, Object> result = new HashMap<>();
        for (int pos = 0; pos < keys.length; pos++) {
            result.put(keys[pos], get(pos));
        }
        return result;
    }

    static <T, U> Tuple2<T, U> tuple(T t, U u) {
        return new Tuple2<>(t, u);
    }

    static <T, U, V> Tuple3<T, U, V> tuple(T t, U u, V v) {
        return new Tuple3<>(t, u, v);
    }

    static <T, U, V, W> Tuple4<T, U, V, W>  tuple(T t, U u, V v, W w) {
        return new Tuple4<>(t, u, v, w);
    }

    static <T, U, V, W, X> Tuple5<T, U, V, W, X>  tuple(T t, U u, V v, W w, X x) {
        return new Tuple5<>(t, u, v, w, x);
    }

    static <T, U, V, W, X, Y> Tuple6<T, U, V, W, X, Y> tuple(T t, U u, V v, W w, X x, Y y){
        return new Tuple6<>(t, u, v, w, x, y);
    }

    static <T, U, V, W, X, Y, Z> Tuple7<T, U, V, W, X, Y, Z> tuple(T t, U u, V v, W w, X x, Y y, Z z){
        return new Tuple7<>(t, u, v, w, x, y, z);
    }

    static <S, T, U, V, W, X, Y, Z> Tuple8<S, T, U, V, W, X, Y, Z> tuple(S s, T t, U u, V v, W w, X x, Y y, Z z){
        return new Tuple8<>(s, t, u, v, w, x, y, z);
    }
}
