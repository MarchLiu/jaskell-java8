package jaskell.parsec;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Eof Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>一月 9, 2016</pre>
 */
public class EofTest extends Base {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: script(State<T> s)
     */
    @Test
    public void testEof() throws Exception {
        State<Character, Integer, Integer> state = newState("hello");

        Eof<Character> eof = new Eof<>();

            new Text("hello").parse(state);
            Object e = eof.parse(state);
        Assert.assertNull(e);
    }
}
