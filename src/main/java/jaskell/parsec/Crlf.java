package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-11.
 * Crlf 即 haskell parsec 的 crlf 算子,匹配 \r\n .
 */
public class Crlf<Status, Tran>
    implements Parsec<String, Character, Status, Tran> {
    @Override
    public String parse(State<Character, Status, Tran> s)
            throws EOFException, ParsecException {
        new Ch<Status, Tran>('\r').parse(s);
        new Newline<Status, Tran>().parse(s);
        return "\r\n";
    }
}
