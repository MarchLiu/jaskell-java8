package jaskell.parsec.common;

import org.junit.jupiter.api.Test;

import static jaskell.parsec.common.Atom.eq;
import static jaskell.parsec.common.Combinator.skip;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SkipTest extends Base {

    /**
     * Method: script(State<E> s)
     */
    @Test
    public void oneSkip() throws Throwable {
        State<Character> state = newState("hello World");
        Skip<Character, Character> skip = skip(eq('h'));
        skip.parse(state);

        assertEquals(1, state.status(), "Now state should skip first item.");
    }

    @Test
    public void stopAtStart() throws Throwable {
        State<Character> state = newState("hello World");
        Skip<Character, Character> skip = skip(eq('e'));
        skip.parse(state);

        assertEquals(0, (int) state.status(), "Now state should stop before first item.");
    }

    /**
     * Check skip space or tab at string start.
     * @throws Exception ParsecException
     */
    @Test
    public void skipSpaces() throws Throwable {
        State<Character> state = newState("\t\t \thello World");
        Parsec<Character, Character> spaces = skip(new ChIn(" \t"));
        spaces.parse(state);

        assertEquals(4, state.status(),
                "Now state should stop after four characters what space or tab.");
    }

    /**
     * Check skip nothing at string start.
     * @throws Exception ParsecException
     */
    @Test
    public void skipNothing() throws Throwable {
        State<Character> state = newState("\nhello World");
        Parsec<Character, Character> spaces = skip(new ChIn(" \t"));
        spaces.parse(state);

        assertEquals(0, state.status(),
                "Now state should stop at start.");
    }
}
