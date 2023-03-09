package jaskell.parsec.common;

import jaskell.parsec.ParsecException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class SpaceTest extends Base {


    /**
     * Method: script(State<E> s)
     */
    @Test
    public void simpleSpace() throws Throwable {
        State<Character> state = newState(" ");
        Parsec<Character, Character> s = new Space();
        Character a = s.parse(state);
        assertEquals(' ', a.charValue());
    }

    @Test
    public void fail() throws Exception {
        State<Character> state = newState("\t");
        Parsec<Character, Character> s = new Space();
        assertThrowsExactly(ParsecException.class,
                () -> s.parse(state),
                "Space script tab char should failed.");
    }
}
