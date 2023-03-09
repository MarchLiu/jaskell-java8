package jaskell.parsec;


import org.junit.jupiter.api.Test;

import static jaskell.parsec.Combinator.skip;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SkipTest extends Base {
    /**
     * Method: script(State<E> s)
     */
    @Test
    public void oneSkip() throws Exception {
        State<Character, Integer, Integer> state = newState("hello World");
        Skip<Character, Character, Integer, Integer> skip = new Skip<>(new Eq<>('h'));
        skip.parse(state);

        assertEquals(1, (int) state.status(), "Now state should skip first item.");
    }

    @Test
    public void stopAtStart() throws Exception {
        State<Character, Integer, Integer> state = newState("hello World");
        Skip<Character, Character, Integer, Integer> skip = new Skip<>(new Eq<>('e'));
        skip.parse(state);

        assertEquals(0, (int) state.status(), "Now state should stop before first item.");
    }

    /**
     * Check skip space or tab at string start.
     *
     * @throws Exception
     */
    @Test
    public void skipSpaces() throws Exception {
        State<Character, Integer, Integer> state = newState("\t\t \thello World");
        Parsec<Character, Character, Integer, Integer> spaces = skip(new ChIn(" \t"));
        spaces.parse(state);

        assertEquals(4, (int) state.status(),
                "Now state should stop after four characters what space or tab.");
    }

    /**
     * Check skip nothing at string start.
     *
     * @throws Exception
     */
    @Test
    public void skipNothing() throws Exception {
        State<Character, Integer, Integer> state = newState("\nhello World");
        Parsec<Character, Character, Integer, Integer> spaces = skip(new ChIn<>(" \t"));
        spaces.parse(state);

        assertEquals(0, (int) state.status(),
                "Now state should stop at start.");
    }
}
