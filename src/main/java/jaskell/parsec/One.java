package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-02.
 * One 成功匹配所有非 eof 的状态.s
 */
public class One<E, Status, Tran> implements Parsec<E, E, Status, Tran> {
    @Override
    public E parse(State<E, Status, Tran> s) throws EOFException, ParsecException {
        return s.next();
    }
}
