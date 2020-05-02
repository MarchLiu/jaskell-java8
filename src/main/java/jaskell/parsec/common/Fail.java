package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-07.
 * Fail 不改变 state , 直接抛出预设的异常.
 */
public class Fail<E> implements Parsec<E, E> {
    private final String message;

    @Override
    public  E parse(State<E> s) throws EOFException, ParsecException {
        throw s.trap(message);
    }

    public Fail(String msg, Object...objects) {
        message = String.format(msg, objects);
    }
}
