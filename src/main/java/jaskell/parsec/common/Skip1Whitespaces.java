package jaskell.parsec.common;

/**
 * Created by march on 16/9/12.
 * SkipSpaces is a parser skip all whitespaces.
 */
public class Skip1Whitespaces
    implements Parsec<Character, Character> {
    private final Parsec<Character, Character> parser = (new Whitespace()).then(new Skip<>(new Whitespace()));
    @Override
    public Character parse(State<Character> s)
            throws Throwable {
        return parser.parse(s);
    }
}
