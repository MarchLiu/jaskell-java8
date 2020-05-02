package jaskell.parsec;


import static jaskell.parsec.Combinator.skip;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SkipTest extends Base {
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }
    /**
     * Method: script(State<E> s)
     */
    @Test
    public void oneSkip() throws Exception {
        State<Character, Integer, Integer> state = newState("hello World");
        Skip<Character, Character, Integer, Integer> skip = new Skip<>(new Eq<>('h'));
        skip.parse(state);

        Assert.assertEquals("Now state should skip first item.", 1, (int) state.status());
    }

    @Test
    public void stopAtStart() throws Exception {
        State<Character, Integer, Integer> state = newState("hello World");
        Skip<Character, Character, Integer, Integer> skip = new Skip<>(new Eq<>('e'));
        skip.parse(state);

        Assert.assertEquals("Now state should stop before first item.", 0, (int) state.status());
    }

    /**
     * Check skip space or tab at string start.
     * @throws Exception
     */
    @Test
    public void skipSpaces() throws Exception {
        State<Character, Integer, Integer> state = newState("\t\t \thello World");
        Parsec<Character, Character, Integer, Integer> spaces = skip(new ChIn(" \t"));
        spaces.parse(state);

        Assert.assertEquals("Now state should stop after four characters what space or tab.", 4, (int) state.status());
    }

    /**
     * Check skip nothing at string start.
     * @throws Exception
     */
    @Test
    public void skipNothing() throws Exception {
        State<Character, Integer, Integer> state = newState("\nhello World");
        Parsec<Character, Character, Integer, Integer> spaces = skip(new ChIn<>(" \t"));
        spaces.parse(state);

        Assert.assertEquals("Now state should stop at start.", 0, (int) state.status());
    }
}
