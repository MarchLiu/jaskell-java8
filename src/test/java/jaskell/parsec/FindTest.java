package jaskell.parsec;

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
    public void simple() throws Exception {
        State<Character, Integer, Integer> state = newState(data);
        Parsec<String,Character, Integer, Integer> parser = new Find<>(new Text<>("find"));
        String re = parser.parse(state);
        Assert.assertEquals("find", re);
    }

    @Test
    public void failed() throws Exception {
        State<Character, Integer, Integer> state = newState(data);
        Parsec<String, Character, Integer, Integer> parser = new Find<>(new Text<>("Fail"));
        try {
            String re = parser.parse(state);
        } catch (Exception e){
            Assert.assertTrue(e instanceof ParsecException);
        }
    }
}