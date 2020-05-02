package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by zhaonf on 16/1/10.
 * EndOfLine 尝试匹配 \n\r 或 \n
 */
public class EndOfLine<Status, Tran>
    implements Parsec<String, Character, Status, Tran> {
    private final Parsec<String, Character, Status, Tran> parsec =
        new Choice<>(new Text<>("\n"), new Text<>("\r\n"));
    @Override
    public String parse(State<Character, Status, Tran> s)
            throws EOFException, ParsecException {
        return parsec.parse(s);
    }
}
