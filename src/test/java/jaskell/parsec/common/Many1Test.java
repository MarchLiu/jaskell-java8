package jaskell.parsec.common;

import static jaskell.parsec.common.Txt.joining;
import static jaskell.parsec.common.Atom.eq;

import jaskell.parsec.ParsecException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Many1 Tester.
 *
 * @author Mars Liu
 */
public class Many1Test extends Base {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void fail() throws Exception {
        State<Character> state = newState("ello");

        Many1<Character, Character> m = new Many1<>(
                eq('h')
        );

        try {
            m.parse(state);
            Assert.fail();
        } catch (ParsecException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void one() throws Exception {
        Parsec<Character, String> m =
                new Many1<>(new Eq<>('h')).bind(joining());
        State<Character> state1 = newState("hello");
        String re = m.parse(state1);
        Assert.assertEquals(re, "h");
    }

    @Test
    public void all() throws Exception {
        Parsec<Character, String> parser =
                new Many1<>(new One<Character>()).bind(joining());
        State<Character> state1 = newState("hello");
        String re = parser.parse(state1);
        Assert.assertEquals(re, "hello");
    }
} 
