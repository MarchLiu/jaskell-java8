package jaskell.parsec.common;

import org.junit.jupiter.api.Test;

import static jaskell.parsec.common.Txt.text;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.io.EOFException;

public class TextTest extends Base {


    /**
     * Method: script(State<E> s)
     */
    @Test
    public void simple() throws Exception {
        State<Character> state = newState("Hello World");
        Text s = new Text("Hello World");
        String a = s.parse(state);
        assertEquals("Hello World", a);
    }

    @Test
    public void less() throws Exception {
        State<Character> state = newState("Hello World");
        Text s = text("Hello");
        String a = s.parse(state);
        assertEquals("Hello", a);
    }

    @Test
    public void more() throws Exception {
        State<Character> state = newState("Hello");
        Text s = text("Hello world");
        assertThrowsExactly(EOFException.class,
                () -> s.parse(state),
                "expect script failed because test data too large.");
    }
}
