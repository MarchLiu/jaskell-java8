package jaskell.parsec;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Eq Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>一月 9, 2016</pre>
 */
public class EqTest extends Base {

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
    public void testEq() throws Exception {
        State<Character, Integer, Integer> state = newState("hello");

        Eq<Character, Integer, Integer> eq = new Eq<>('h');
        Character c = eq.parse(state);
        Assert.assertEquals(c, new Character('h'));
    }


} 
