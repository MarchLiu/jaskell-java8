package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-07.
 * 跳过指定算子 1 到多次.
 */
public class Skip1<T, E>
    implements Parsec<T, E> {
    private final Parsec<T, E> psc;

    @Override
    public T parse(State<E> s) throws EOFException, ParsecException {
        psc.parse(s);
        new Skip<>(psc).parse(s);
        return null;
    }

    public Skip1(Parsec<T, E> psc) {
        this.psc = psc;
    }
}