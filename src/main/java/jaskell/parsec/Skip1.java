package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-07.
 * 跳过指定算子 1 到多次.
 */
public class Skip1<T, E> implements Parsec<T, E> {
    private Parsec<T, E> psc;

    @Override
    public <Status, Tran, S extends State<E, Status, Tran>>  T parse(S s) throws EOFException, ParsecException {
        psc.parse(s);
        new Skip<>(psc).parse(s);
        return null;
    }

    public Skip1(Parsec<T, E> psc) {
        this.psc = psc;
    }
}