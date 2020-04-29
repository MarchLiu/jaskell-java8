package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-11.
 * Crlf 即 haskell parsec 的 crlf 算子,匹配 \r\n .
 */
public class Crlf implements Parsec<String, Character> {
    @Override
    public <Status, Tran, S extends State<Character, Status, Tran>> String parse(S s)
            throws EOFException, ParsecException {
        new Ch('\r').parse(s);
        new Newline().parse(s);
        return "\r\n";
    }
}
