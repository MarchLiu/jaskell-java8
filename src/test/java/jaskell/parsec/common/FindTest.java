package jaskell.parsec.common;

import static jaskell.parsec.common.Txt.text;

import jaskell.parsec.ParsecException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by march on 16/9/9.
 * JUnit tests for Find parser.
 */
public class FindTest extends Base {
    private String data;

    @Before
    public void before() {
        data = "It is a junit test case for find parsec.";
    }

    @Test
    public void simple() throws Throwable {
        State<Character> state = newState(data);
        Parsec<Character, String> parser = new Find<>(text("find"));
        String re = parser.parse(state);
        Assert.assertEquals("find", re);
    }

    @Test
    public void failed() throws Exception {
        State<Character> state = newState(data);
        Parsec<Character, String> parser = new Find<>(new Text("Fail"));
        try {
            String re = parser.parse(state);
        } catch (Throwable e){
            Assert.assertTrue(e instanceof ParsecException);
        }
    }
}