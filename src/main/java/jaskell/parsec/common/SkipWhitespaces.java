package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;

/**
 * Created by march on 16/9/12.
 * SkipSpaces is a parser skip all whitespaces.
 */
public class SkipWhitespaces
    implements Parsec<Character, Character> {
    private final Parsec<Character, Character> parser = new Skip<>(new Whitespace());
    @Override
    public Character parse(State<Character> s)
            throws Throwable {
        return parser.parse(s);
    }
}
