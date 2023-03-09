package jaskell.parsec;


import org.junit.jupiter.api.Test;

import static jaskell.parsec.Combinator.skip1;
import static jaskell.parsec.Txt.text;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by Mars Liu on 16/1/10.
 *
 * Skip1 tests
 */
public class Skip1Test extends Base {
    /**
     * Method: script(State<E> s)
     */
    @Test
    public void simple() throws Exception {
        State<Character, Integer, Integer> state = newState("left right left right");
        Parsec<Character, String, Integer, Integer> parser = skip1(text("left"));
        String re = parser.parse(state);
        assertNull(re, "Expect string \"left\".");
    }

    @Test
    public void simpleStatus() throws Exception {
        State<Character, Integer, Integer> state = newState("left right left right");
        Parsec<Character, String, Integer, Integer> parser = skip1(text("left "));
        parser.parse(state);
        Integer status = state.status();
        assertEquals(5, status,  "Expect string \"left\".");
    }

    @Test
    public void statusMore() throws Exception {
        State<Character, Integer, Integer> state = newState("left left right right");
        Parsec<Character, String, Integer, Integer> parser = skip1(text("left "));
        parser.parse(state);
        Integer status = state.status();
        assertEquals(10, status, "Expect string \"left\".");
    }

    @Test
    public void fail() throws Exception {
        State<Character, Integer, Integer> state = newState("right right left left");
        Parsec<Character, String, Integer, Integer> parser = skip1(text("left "));
        try {
            parser.parse(state);
        } catch (Exception e) {
            Integer status = state.status();
            assertEquals(1, status, "Expect failed and stop at 1.");

            assertInstanceOf(ParsecException.class, e, "Expect a ParsecException");
        }
    }
}
