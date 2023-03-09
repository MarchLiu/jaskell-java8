package jaskell.parsec.common;

import jaskell.parsec.ParsecException;
import org.junit.jupiter.api.Test;

import static jaskell.parsec.common.Combinator.skip1;
import static jaskell.parsec.common.Txt.text;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Mars Liu on 16/1/10.
 * <p>
 * Skip1 tests
 */
public class Skip1Test extends Base {
    /**
     * Method: script(State<E> s)
     */
    @Test
    public void simple() throws Throwable {
        State<Character> state = newState("left right left right");
        Parsec<Character, String> parser = skip1(text("left"));
        String re = parser.parse(state);
        assertNull(re, "Expect string \"left\".");
    }

    @Test
    public void simpleStatus() throws Throwable {
        State<Character> state = newState("left right left right");
        Parsec<Character, String> parser = skip1(text("left "));
        parser.parse(state);
        Integer status = state.status();
        assertEquals(5, status, "Expect string \"left\".");
    }

    @Test
    public void statusMore() throws Throwable {
        State<Character> state = newState("left left right right");
        Parsec<Character, String> parser = skip1(text("left "));
        parser.parse(state);
        Integer status = state.status();
        assertEquals(10, status, "Expect string \"left\".");
    }

    @Test
    public void fail() throws Exception {
        State<Character> state = newState("right right left left");
        Parsec<Character, String> parser = skip1(text("left "));

        assertThrowsExactly(ParsecException.class,
                () -> parser.parse(state),
                "Expect a ParsecException");
        assertEquals(1, state.status(), "Expect failed and stop at 1.");
    }
}
