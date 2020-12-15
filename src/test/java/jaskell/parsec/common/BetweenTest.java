package jaskell.parsec.common;

import static jaskell.parsec.common.Atom.eq;
import static jaskell.parsec.common.Atom.ne;
import static jaskell.parsec.common.Combinator.between;
import static jaskell.parsec.common.Combinator.many;
import static jaskell.parsec.common.Txt.ch;

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
    public void simple() throws Throwable {

        State<Character> state = newState("hello");

        Parsec<Character, Character> bmw = between(
                eq('h'),
                eq('l'),
                eq('e')
        );

        Character c = bmw.parse(state);
        Assert.assertEquals('e', (char) c);
    }

    @Test
    public void brackets() throws Throwable {

        State<Character> state = newState("[hello]");


        Parsec<Character, String> parser = between(
                ch('['),
                ch(']'),
                many(ne(']'))
        ).bind(new JoinText());

        String re = parser.parse(state);
        Assert.assertEquals("hello", re);
    }


} 
