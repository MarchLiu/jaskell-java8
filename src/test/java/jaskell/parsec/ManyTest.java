package jaskell.parsec;

import org.junit.jupiter.api.Test;

import static jaskell.parsec.Atom.eq;
import static jaskell.parsec.Combinator.many;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.List;

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
        State<Character, Integer, Integer> state = newState("hhello");

        Many<Character, Character, Integer, Integer> m = many(eq('h'));

        List<Character> a = m.parse(state);
        assertEquals(a.size(), 2);
    }
}
