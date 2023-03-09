package jaskell.parsec;


import org.junit.jupiter.api.Test;

import java.io.EOFException;

import static org.junit.jupiter.api.Assertions.*;

public class TextTest extends Base {


    /**
     * Method: script(State<E> s)
     */
    @Test
    public void simple() throws Exception {
        State<Character, Integer, Integer> state = newState("Hello World");
        Text<Integer, Integer> s = new Text<>("Hello World");
        String a =  s.parse(state);
        assertEquals(a,"Hello World");
    }

    @Test
    public void less() throws Exception {
        State<Character, Integer, Integer> state = newState("Hello World");
        Text<Integer, Integer> s = new Text<>("Hello");
        String a =  s.parse(state);
        assertEquals(a,"Hello");
    }

    @Test
    public void more() throws Exception {
        State<Character, Integer, Integer> state = newState("Hello");
        Text<Integer, Integer> s = new Text<>("Hello world");
        try {
            s.parse(state);
            fail("expect script failed because test data too large.");
        } catch (EOFException e) {
            assertTrue(true);
        }
    }
}
