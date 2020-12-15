package jaskell.parsec.common;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * ManyTil Tester.
 *
 * @author Mars Liu
 * @since 2016-09-12
 */
public class ManyTillTest extends Base {

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
    public void TestManyTill() throws Throwable {
        State<Character> state = newState("hhhhhhlhhhll");

        ManyTill<Character, Character, Character> m = new ManyTill<>(
                new Eq<>('h'),
                new Eq<>('l')
        );

        List<Character> a = m.parse(state);
        Assert.assertEquals(a.size(), 6);
    }


} 
