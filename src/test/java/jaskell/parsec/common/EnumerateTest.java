package jaskell.parsec.common;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Many Tester.
 *
 * @author Mars Liu
 */
public class EnumerateTest extends Base {


    /**
     * Method: script(State<E> s)
     */
    @Test
    public void simple() throws Exception {
        State<Character> state = newState("abcdef");

        Enumerate<Character, Character> abc = Txt.enumerate("abc");

        Set<Character> result = abc.parse(state);

        assertTrue(result.contains('a'));
        assertTrue(result.contains('b'));
        assertTrue(result.contains('c'));
        assertEquals(3, state.status());
    }

    @Test
    public void more() throws Exception {
        State<Character> state = newState("bcdeaabfgh");

        Enumerate<Character, Character> abc = Txt.enumerate("abc");

        Set<Character> result = abc.parse(state);

        assertTrue(result.contains('b'));
        assertTrue(result.contains('c'));
        assertEquals(2, state.status());
    }

    @Test
    public void sepBy() throws Exception {
        State<Character> state = newState("a|d|b|c, f");

        Enumerate<Character, Character> abc = Txt.enumerate("abcd", '|');

        Set<Character> result = abc.parse(state);

        assertTrue(result.contains('a'));
        assertTrue(result.contains('b'));
        assertTrue(result.contains('c'));
        assertTrue(result.contains('d'));
    }
}
