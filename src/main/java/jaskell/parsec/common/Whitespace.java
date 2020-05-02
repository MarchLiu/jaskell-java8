package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-07.
 * Whitespace 匹配空白字符
 */
public class Whitespace
    implements Parsec<Character, Character> {
    @Override
    public Character parse(State<Character> s)
            throws EOFException, ParsecException {
        Character re = s.next();
        if (Character.isWhitespace(re)) {
            return re;
        } else {
            String message = String.format("Expect %c is whitespace.", re);
            throw s.trap(message);
        }
    }
}
