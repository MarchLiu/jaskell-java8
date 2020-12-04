package jaskell.parsec;

import java.util.Set;

/**
 * Created by march on 16/9/12.
 * static util class for atom parsers.
 */
public class Atom {
    public static <E, Status, Tran> Parsec <E, E, Status, Tran> one()  {
        return new One<>();
    }

    public static <E, Status, Tran, S extends State<E, Status, Tran>> Parsec<E, E, Status, Tran> eof() {
        return new Eof<>();
    }

    public static <E, T, Status, Tran> Return<E, T, Status, Tran> pack(T value) {
        return new Return<>(value);
    }

    public static <E, Status, Tran> Fail<E, Status, Tran> fail(String message, Object...objects) {
        return new Fail<>(message, objects);
    }

    public static <E, Status, Tran> Eq<E, Status, Tran> eq(E item) {
        return new Eq<>(item);
    }

    public static <E, Status, Tran> Ne<E, Status, Tran> ne(E item) {
        return new Ne<>(item);
    }

    public static <E, Status, Tran> OneOf<E, Status, Tran> oneOf(Set<E> data) {
        return new OneOf<>(data);
    }

    public static <E, Status, Tran> NoneOf<E, Status, Tran> noneOf(Set<E> data) {
        return new NoneOf<>(data);
    }
}
