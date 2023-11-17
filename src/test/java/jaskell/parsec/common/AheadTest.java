package jaskell.parsec.common;

import jaskell.parsec.ParsecException;
import org.junit.jupiter.api.Test;

import static jaskell.parsec.common.Combinator.ahead;
import static jaskell.parsec.common.Txt.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

/**
 * Created by Mars Liu on 16/9/15.
 * <p>
 * Tests About look behind.
 */
public class AheadTest extends Base {

    @Test
    public void simple() throws Exception {
        State<Character> state = newState("this is a string data.");
        Parsec<Character, String> parser =
                text("this").over(new Ahead<>(text(" is")));

        String re = parser.parse(state);

        assertEquals("this", re);
        assertEquals(4, state.status(), "Expect status stop after this but is.");
    }

    @Test
    public void result() throws Exception {
        State<Character> state = newState("this is a string data.");
        Parsec<Character, String> parser =
                text("this").then(space()).then(new Ahead<>(text("is")));

        String re = parser.parse(state);

        assertEquals(re, "is");
        assertEquals(5, state.status(), "Expect status stop after this (5) but is.");
    }

    @Test
    public void fail() throws Exception {
        State<Character> state = newState("this is a string.");
        Parsec<Character, String> parser =
                text("this").then(space()).then(ahead(text(" is")));

        assertThrowsExactly(ParsecException.class,
                () -> parser.parse(state),
                "Expect the parser fail when try match \" is\"");
        assertEquals(5, state.status(), "Expect status stop after this (5) but is.");

    }
}
