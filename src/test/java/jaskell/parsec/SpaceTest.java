package jaskell.parsec;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class SpaceTest extends Base {


    /**
     * Method: script(State<E> s)
     */
    @Test
    public void simpleSpace() throws Exception {
        State<Character, Integer, Integer> state = newState(" ");
        Parsec<Character, Character, Integer, Integer> s = new Space<>();
        Character a = s.parse(state);
        assertEquals(' ', a.charValue());
    }

    @Test
    public void fail() throws Exception {
        State<Character, Integer, Integer> state = newState("\t");
        Parsec<Character, Character, Integer, Integer> s = new Space<>();
        assertThrowsExactly(ParsecException.class,
                () -> s.parse(state),
                "Space script tab char should failed.");
    }
}
