package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-07.
 * Digit 判断下一个项是否是一个表示数字的字符.它仅接受 Character/char .
 */
public class Digit
    implements Parsec<Character, Character> {
    @Override
    public Character parse(State<Character> s)
            throws EOFException, ParsecException {
        Character re = s.next();
        if (Character.isDigit(re)) {
            return re;
        } else {
            String message = String.format("Expect %c is digit.", re);
            throw s.trap(message);
        }
    }
}
