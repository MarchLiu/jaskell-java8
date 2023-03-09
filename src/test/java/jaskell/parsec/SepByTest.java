package jaskell.parsec;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SepByTest extends Base {

    /**
     * Method: script(State<E> s)
     */
    @Test
    public void TestSepBy() throws Exception {
        State<Character, Integer, Integer> state = newState("hlhlhlhlhlhll");

        SepBy<Character, Character, Character, Integer, Integer> m = new SepBy<>(
                new Eq<>('h'),
                new Eq<>('l')
        );

        List<Character> a = m.parse(state);
        assertEquals(a.size(), 6);
    }

} 
