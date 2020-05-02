package jaskell.parsec;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Between Tester.
 *
 * @author Mars Liu
 *
 *
 */
public class BetweenTest extends Base {

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

        State<Character, Integer, Integer> state = newState("hello");


        Between<Character, Character, Character, Character, Integer, Integer> bmw = new Between<>(
                new Eq<>('h'),
                new Eq<>('l'),
                new Eq<>('e')
        );

        Character c = bmw.parse(state);
        Assert.assertEquals('e', (char) c);
    }

    @Test
    public void brackets() throws Exception {

        State<Character, Integer, Integer> state = newState("[hello]");


        Parsec<String, Character, Integer, Integer> parser = new Between<>(
                new Ch<Integer, Integer>('['),
                new Ch<>(']'),
                new Many1<>(new Ne<>(']'))
        ).bind(new JoinText<>());

        String re = parser.parse(state);
        Assert.assertEquals("hello", re);
    }


} 
