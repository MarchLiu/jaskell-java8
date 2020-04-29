package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-07.
 * Space 匹配空格
 */
public class Space implements Parsec<Character, Character>{
    @Override
    public <Status, Tran, S extends State<Character, Status, Tran>> Character parse(S s)
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

