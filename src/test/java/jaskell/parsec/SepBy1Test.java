package jaskell.parsec;

import static jaskell.parsec.Combinator.sepBy1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SepBy1Test extends Base {

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
    public void TestSepBy1() throws Exception {
        State<Character, Integer, Integer> state = newState("hlhlhlhlhlhll");

        SepBy1<Character, Character, Character, Integer, Integer> m =
                sepBy1(new Ch<>('h'), new Ch<>('l'));

        List<Character> a = m.parse(state);
        Assert.assertEquals(a.size(), 6);

        State<Character, Integer, Integer> state1 = newState("hlh,h.hlhlhll");

        List<Character> b = m.parse(state1);
        Assert.assertEquals(b.size(), 2);

        try {
            List<Character> c = m.parse(state1);
            String message = String.format("Expect a exception but %s", c);
            Assert.fail(message);
        } catch (ParsecException e) {
            Assert.assertTrue(true);
        }
    }


} 
