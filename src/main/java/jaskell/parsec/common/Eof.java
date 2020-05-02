package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-02.
 * Eof 期待 state 的 next 操作取到 eof 状态.
 */
public class Eof<E> implements Parsec<E, E> {
    @Override
    public E parse(State<E> s) throws EOFException, ParsecException {
        try{
            E re = s.next();
            String message = String.format("Expect eof but %s", re);
            throw s.trap(message);
        } catch (EOFException e) {
            return null;
        }
    }
}
