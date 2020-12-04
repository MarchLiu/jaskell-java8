package jaskell.parsec;

import static jaskell.parsec.Txt.ch;

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
    public void simpleCrlf() throws Exception {
        State<Character, Integer, Integer> state = newState("\r\n");
        Parsec<Character, String, Integer, Integer> crlf = new Crlf<>();

        String re = crlf.parse(state);
        Assert.assertEquals(re, "\r\n");
    }

    /**
     * Method: script(State<E> s)
     */
    @Test
    public void simpleNl() throws Exception {
        State<Character, Integer, Integer> state = newState("\r\n");

        Parsec<Character, Character, Integer, Integer> enter = new Newline<>();

        Character c = new Ch<Integer, Integer>('\r').then(enter).parse(state);

        Assert.assertEquals(c.charValue(), '\n');
    }
}
