package jaskell.parsec.common;

import static jaskell.parsec.common.Txt.ch;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NewlineTest extends Base {
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void simpleCrlf() throws Throwable {
        State<Character> state = newState("\r\n");
        Parsec<Character, String> crlf = new Crlf();

        String re = crlf.parse(state);
        Assert.assertEquals(re, "\r\n");
    }

    /**
     * Method: script(State<E> s)
     */
    @Test
    public void simpleNl() throws Throwable {
        State<Character> state = newState("\r\n");

        Parsec<Character, Character> enter = new Newline();

        Character c = ch('\r').then(enter).parse(state);

        Assert.assertEquals(c.charValue(), '\n');
    }
}
