package jaskell.parsec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.EOFException;

public class TextTest extends Base {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: script(State<E> s)
     */
    @Test
    public void simple() throws Exception {
        State<Character, Integer, Integer> state = newState("Hello World");
        Text s = new Text("Hello World");
        String a =  s.parse(state);
        assertEquals(a,"Hello World");
    }

    @Test
    public void less() throws Exception {
        State<Character, Integer, Integer> state = newState("Hello World");
        Text s = new Text("Hello");
        String a =  s.parse(state);
        assertEquals(a,"Hello");
    }

    @Test
    public void more() throws Exception {
        State<Character, Integer, Integer> state = newState("Hello");
        Text s = new Text("Hello world");
        try {
            s.parse(state);
            fail("expect script failed because test data too large.");
        } catch (EOFException e) {
            assertTrue(true);
        }
    }
}
