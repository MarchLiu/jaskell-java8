package jaskell.parsec.common;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Eof Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>一月 9, 2016</pre>
 */
public class EofTest extends Base {

    /**
     * Method: script(State<T> s)
     */
    @Test
    public void testEof() throws Exception {
        State<Character> state = newState("hello");

        Eof<Character> eof = new Eof<>();

            new Text("hello").parse(state);
            Object e = eof.parse(state);
        assertNull(e);
    }
}
