package jaskell.parsec;

import java.io.CharArrayReader;
import java.io.EOFException;

/**
 * Created by march on 16/9/12.
 * SkipSpaces is a parser skip all spaces.
 */
public class SkipSpaces<Status, Tran>
    implements Parsec<Character, Character, Status, Tran> {
    private final Parsec<Character, Character, Status, Tran> parser = new Skip<>(new Whitespace<>());
    @Override
    public Character parse(State<Character, Status, Tran> s)
            throws EOFException, ParsecException {
        return parser.parse(s);
    }
}
