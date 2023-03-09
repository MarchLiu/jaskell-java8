package jaskell.parsec;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OneTest extends Base {

    /**
     * Method: script(State<T> s)
     */
    @Test
    public void testOne() throws Exception {
        State<Character, Integer, Integer> state = newState("hello");

        One<Character, Integer, Integer> one = new One<>();

        try {
            Character c = one.parse(state);
            assertEquals('h', (char) c);
        } catch (ParsecException e) {
            assertTrue(true);
        }
    }

} 
