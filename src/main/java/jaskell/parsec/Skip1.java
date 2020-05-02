package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-07.
 * 跳过指定算子 1 到多次.
 */
public class Skip1<T, E, Status, Tran>
    implements Parsec<T, E, Status, Tran> {
    private final Parsec<T, E, Status, Tran> psc;

    @Override
    public T parse(State<E, Status, Tran> s) throws EOFException, ParsecException {
        psc.parse(s);
        new Skip<>(psc).parse(s);
        return null;
    }

    public Skip1(Parsec<T, E, Status, Tran> psc) {
        this.psc = psc;
    }
}