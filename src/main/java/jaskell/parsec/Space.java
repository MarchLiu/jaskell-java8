package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-07.
 * Space 匹配空格
 */
public class Space<Status, Tran>
    implements Parsec<Character, Character, Status, Tran>{
    @Override
    public Character parse(State<Character, Status, Tran> s)
            throws EOFException, ParsecException {
        Character re = s.next();
        if (Character.isSpaceChar(re)) {
            return re;
        } else {
            String message = String.format("Expect %c is space.", re);
            throw s.trap(message);
        }
    }
}

