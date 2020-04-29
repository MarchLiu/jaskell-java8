package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-02.
 * One 成功匹配所有非 eof 的状态.s
 */
public class One<E> implements Parsec<E, E> {
    @Override
    public <Status, Tran, S extends State<E, Status, Tran>> E parse(S s) throws EOFException, ParsecException {
        return s.next();
    }
}
