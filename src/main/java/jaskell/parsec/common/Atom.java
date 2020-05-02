package jaskell.parsec.common;

import java.util.Set;

/**
 * Created by march on 16/9/12.
 * static util class for atom parsers.
 */
public class Atom {
    public static <E> One<E> one()  {
        return new One<>();
    }

    public static <E> Eof<E> eof() {
        return new Eof<>();
    }

    public static <T, E> Return<T, E> pack(T value) {
        return new Return<>(value);
    }

    public static <E> Fail<E>
    fail(String message, Object...objects) {
        return new Fail<>(message, objects);
    }

    public static <E> Eq<E> eq(E item) {
        return new Eq<>(item);
    }

    public static <E> Ne<E> ne(E item) {
        return new Ne<>(item);
    }

    public static <E> OneOf<E> oneOf(Set<E> data) {
        return new OneOf<>(data);
    }

    public static <E> NoneOf<E> noneOf(Set<E> data) {
        return new NoneOf<>(data);
    }
}
