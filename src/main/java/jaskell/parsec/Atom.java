package jaskell.parsec;

import java.util.Set;

/**
 * Created by march on 16/9/12.
 * static util class for atom parsers.
 */
public class Atom {
    public static <E>  Parsec <E, E> one()  {
        return new One<>();
    }

    public static <E> Parsec<E, E> eof() {
        return new Eof<>();
    }

    public static <T, E> Parsec<T, E> pack(T value) {
        return new Return<>(value);
    }

    public static <E> Parsec<E, E> fail(String message, Object...objects) {
        return new Fail<>(message, objects);
    }

    public static <E> Parsec<E, E> eq(E item) {
        return new Eq<>(item);
    }

    public static <E> Parsec<E, E> ne(E item) {
        return new Ne<>(item);
    }

    public static <E> Parsec<E, E> oneOf(Set<E> data) {
        return new OneOf<>(data);
    }

    public static <E> Parsec<E, E> noneOf(Set<E> data) {
        return new NoneOf<>(data);
    }
}
