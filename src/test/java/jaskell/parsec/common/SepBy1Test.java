package jaskell.parsec.common;

import static jaskell.parsec.common.Txt.ch;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import jaskell.parsec.ParsecException;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SepBy1Test extends Base {


    /**
     * Method: script(State<E> s)
     */
    @Test
    public void TestSepBy1() throws Exception {
        State<Character> state = newState("hlhlhlhlhlhll");

        SepBy1<Character, Character, Character> m =
                new SepBy1<>(ch('h'), ch('l'));

        List<Character> a = m.parse(state);
        assertEquals(6, a.size());

        State<Character> state1 = newState("hlh,h.hlhlhll");

        List<Character> b = m.parse(state1);
        assertEquals(2, b.size());

        assertThrowsExactly(ParsecException.class,
                () -> m.parse(state1),
                "Expect a exception");
    }

} 
