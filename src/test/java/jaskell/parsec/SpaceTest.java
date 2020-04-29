package jaskell.parsec;

import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceTest extends Base {

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
    public void simpleSpace() throws Exception {
        State<Character, Integer, Integer> state = newState(" ");
        Parsec<Character, Character> s = new Space();
        Character a =  s.parse(state);
        Assert.assertEquals(a.charValue(), ' ');
    }

    @Test
    public void fail() throws Exception {
        State<Character, Integer, Integer> state = newState("\t");
        Parsec<Character, Character> s = new Space();
        try {
            s.parse(state);
            Assert.fail("Space script tab char should failed.");
        } catch (Exception e) {
            Assert.assertThat("We should catch a ParsecException.", e, IsInstanceOf.instanceOf(ParsecException.class));
        }
    }
}
