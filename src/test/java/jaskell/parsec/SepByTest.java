package jaskell.parsec;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SepByTest extends Base {

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
    public void TestSepBy() throws Exception {
        State<Character, Integer, Integer> state = newState("hlhlhlhlhlhll");

        SepBy<Character, Character, Character> m = new SepBy<>(
                new Eq<>('h'),
                new Eq<>('l')
        );

        List<Character> a = m.parse(state);
        Assert.assertEquals(a.size(), 6);
    }

} 
