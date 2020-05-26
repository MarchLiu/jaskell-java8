package jaskell.parsec.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import jaskell.parsec.ParsecException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IntTest extends Base {
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void simple() throws Exception {
        State<Character> state = newState("23413214");

        Int intParser = new Int();

        String s = intParser.parse(state);

        assertEquals("23413214", s);
    }

    @Test
    public void stop() throws Exception {
        State<Character> state = newState("23413a214");

        Int intParse = new Int();

        String s = intParse.parse(state);

        assertEquals("23413", s);
    }

    @Test
    public void fail() throws Exception {
        State<Character> state = newState("x2344");

        Int intParse = new Int();

        try {
            String s = intParse.parse(state);
            throw new Exception("Expect fail when no digit at start.");
        }catch (ParsecException e) {
            assertTrue(true);
        }
    }

    @Test
    public void nSimple() throws Exception {
        State<Character> state = newState("-23413214");

        Int intParser = new Int();

        String s = intParser.parse(state);

        assertEquals("-23413214", s);
    }

    @Test
    public void nStop() throws Exception {
        State<Character> state = newState("-23413a214");

        Int intParser = new Int();

        String s = intParser.parse(state);

        assertEquals("-23413", s);
    }

    @Test
    public void nFail() throws Exception {
        State<Character> state = newState("-x2344");

        Int intParser = new Int();

        try {
            String s = intParser.parse(state);
            throw new Exception("Expect fail when no digit at start.");
        }catch (ParsecException e) {
            assertTrue(true);
        }
    }

}
