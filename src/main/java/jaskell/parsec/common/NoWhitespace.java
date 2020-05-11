package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-07.
 * Whitespace 匹配空白字符
 */
public class NoWhitespace implements Parsec<Character, Character> {
    @Override
    public Character parse(State<Character> s)
            throws EOFException, ParsecException {
        Character re = s.next();
        if (Character.isWhitespace(re)) {
            String message = String.format("Expect one char is not whitespace but get %c.", re);
            throw s.trap(message);
        } else {
            return re;
        }
    }
}
