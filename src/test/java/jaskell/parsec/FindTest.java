package jaskell.parsec;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by march on 16/9/9.
 * JUnit tests for Find parser.
 */
public class FindTest extends Base {
    private static String data;

    @BeforeAll
    public static void before() {
        data = "It is a junit test case for find parsec.";
    }

    @Test
    public void simple() throws Exception {
        State<Character, Integer, Integer> state = newState(data);
        Parsec<Character, String, Integer, Integer> parser = new Find<>(new Text<>("find"));
        String re = parser.parse(state);
        assertEquals("find", re);
    }

    @Test
    public void failed() throws Exception {
        State<Character, Integer, Integer> state = newState(data);
        Parsec<Character, String, Integer, Integer> parser = new Find<>(new Text<>("Fail"));
        try {
            String re = parser.parse(state);
        } catch (Exception e){
            assertTrue(e instanceof ParsecException);
        }
    }
}