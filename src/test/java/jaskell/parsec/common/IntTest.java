package jaskell.parsec.common;


import jaskell.parsec.Parsec;
import jaskell.parsec.ParsecException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class IntTest extends Base {

    @Test
    public void simple() throws Throwable {
        State<Character> state = newState("23413214");

        Int intParser = new Int();

        String s = intParser.parse(state);

        assertEquals("23413214", s);
    }

    @Test
    public void stop() throws Throwable {
        State<Character> state = newState("23413a214");

        Int intParse = new Int();

        String s = intParse.parse(state);

        assertEquals("23413", s);
    }

    @Test
    public void fail() throws Exception {
        State<Character> state = newState("x2344");

        Int intParse = new Int();

        assertThrowsExactly(ParsecException.class,
                () -> intParse.parse(state),
                "Expect fail when no digit at start.");
    }

    @Test
    public void nSimple() throws Throwable {
        State<Character> state = newState("-23413214");

        Int intParser = new Int();

        String s = intParser.parse(state);

        assertEquals("-23413214", s);
    }

    @Test
    public void nStop() throws Throwable {
        State<Character> state = newState("-23413a214");

        Int intParser = new Int();

        String s = intParser.parse(state);

        assertEquals("-23413", s);
    }

    @Test
    public void nFail() throws Exception {
        State<Character> state = newState("-x2344");

        Int intParser = new Int();

        assertThrowsExactly(ParsecException.class,
                () -> intParser.parse(state),
                "Expect fail when no digit at start.");
    }

}
