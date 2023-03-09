package jaskell.parsec;

import org.junit.jupiter.api.Test;

import static jaskell.parsec.Combinator.sepBy1;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class SepBy1Test extends Base {

    /**
     * Method: script(State<E> s)
     */
    @Test
    public void TestSepBy1() throws Exception {
        State<Character, Integer, Integer> state = newState("hlhlhlhlhlhll");

        SepBy1<Character, Character, Character, Integer, Integer> m =
                sepBy1(new Ch<>('h'), new Ch<>('l'));

        List<Character> a = m.parse(state);
        assertEquals(6, a.size());

        State<Character, Integer, Integer> state1 = newState("hlh,h.hlhlhll");

        List<Character> b = m.parse(state1);
        assertEquals(2, b.size());

        try {
            List<Character> c = m.parse(state1);
            String message = String.format("Expect a exception but %s", c);
            fail(message);
        } catch (ParsecException e) {
            assertTrue(true);
        }
    }


} 
