package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-07.
 * Digit 判断下一个项是否是一个表示数字的字符.它仅接受 Character/char .
 */
public class Digit<Status, Tran>
    implements Parsec<Character, Character, Status, Tran> {
    @Override
    public Character parse(State<Character, Status, Tran> s)
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
