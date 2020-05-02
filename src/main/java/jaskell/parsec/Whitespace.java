package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-07.
 * Whitespace 匹配空白字符
 */
public class Whitespace<Status, Tran>
    implements Parsec<Character, Character, Status, Tran>{
    @Override
    public Character parse(State<Character, Status, Tran> s)
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
