package jaskell.parsec.common;

/**
 * Created by march on 16/9/12.
 * SkipSpaces is a parser skip all spaces.
 */
public class Skip1Spaces
    implements Parsec<Character, Character> {
    private final Parsec<Character, Character> parser = (new Space()).then(new Skip<>(new Space()));

    @Override
    public Character parse(State<Character> s)
            throws Throwable {
        return parser.parse(s);
    }
}
