package jaskell.parsec.common;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SepByTest extends Base {


    /**
     * Method: script(State<E> s)
     */
    @Test
    public void TestSepBy() throws Exception {
        State<Character> state = newState("hlhlhlhlhlhll");

        SepBy<Character, Character, Character> m = new SepBy<>(
                new Eq<>('h'),
                new Eq<>('l')
        );

        List<Character> a = m.parse(state);
        assertEquals(6, a.size());
    }

} 
