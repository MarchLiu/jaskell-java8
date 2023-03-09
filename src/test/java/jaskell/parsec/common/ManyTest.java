package jaskell.parsec.common;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Many Tester.
 *
 * @author Mars Liu
 */
public class ManyTest extends Base {


    /**
     * Method: script(State<E> s)
     */
    @Test
    public void simple() throws Exception {
        State<Character> state = newState("hhello");

        Many<Character, Character> m = new Many<>(new Eq<>('h'));

        List<Character> a = m.parse(state);
        assertEquals(2, a.size());
    }
}
