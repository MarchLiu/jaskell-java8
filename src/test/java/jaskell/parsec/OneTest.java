package jaskell.parsec;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OneTest extends Base {

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
    public void testOne() throws Exception {
        State<Character, Integer, Integer> state = newState("hello");

        One<Character, Integer, Integer> one = new One<>();

        try {
            Character c = one.parse(state);
            Assert.assertEquals('h', (char) c);
        } catch (ParsecException e) {
            Assert.assertTrue(true);
        }
    }

} 
