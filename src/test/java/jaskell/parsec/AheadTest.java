package jaskell.parsec;

import org.junit.jupiter.api.Test;

import static jaskell.parsec.Txt.space;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


/**
 * Created by Mars Liu on 16/9/15.
 * <p>
 * Tests About look behind.
 */
public class AheadTest extends Base {

    @Test
    public void simple() throws Exception {
        State<Character, Integer, Integer> state = newState("this is a string data.");
        Parsec<Character, String, Integer, Integer> parser =
                new Text<Integer, Integer>("this").over(new Ahead<>(new Text<>(" is")));

        String re = parser.parse(state);

        assertEquals(re, "this");
        assertEquals(4, state.status(), "Expect status stop after this but is. ");
    }

    @Test
    public void result() throws Exception {
        State<Character, Integer, Integer> state = newState("this is a string data.");
        Parsec<Character, String, Integer, Integer> parser =
                new Text<Integer, Integer>("this").then(space()).then(new Ahead<>(new Text<>("is")));

        String re = parser.parse(state);

        assertEquals(re, "is");
        assertEquals(5, state.status(), "Expect status stop after this (5) but is. ");
    }

    @Test
    public void fail() throws Exception {
        State<Character, Integer, Integer> state = newState("this is a string.");
        Parsec<Character, String, Integer, Integer> parser =
                new Text<Integer, Integer>("this").then(space()).then(new Ahead<>(new Text<>(" is")));

        try {
            String re = parser.parse(state);
        } catch (Exception e) {
            assertEquals(5, state.status(), "Expect status stop after this (5) but is. ");
            assertInstanceOf(ParsecException.class, e, "Expect the parser fail when try match \" is\"");
        }
    }
}
