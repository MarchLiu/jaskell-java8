package jaskell.parsec.common;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Many Tester.
 *
 * @author Mars Liu
 */
public class ManyTest extends Base {

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
        State<Character> state = newState("hhello");

        Many<Character, Character> m = new Many<>(new Eq<>('h'));

        List<Character> a = m.parse(state);
        Assert.assertEquals(a.size(), 2);
    }
}
