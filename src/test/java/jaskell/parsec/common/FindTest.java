package jaskell.parsec.common;

import static jaskell.parsec.common.Txt.text;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import jaskell.parsec.ParsecException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


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
        State<Character> state = newState(data);
        Parsec<Character, String> parser = new Find<>(text("find"));
        String re = parser.parse(state);
        assertEquals("find", re);
    }

    @Test
    public void failed() throws Exception {
        State<Character> state = newState(data);
        Parsec<Character, String> parser = new Find<>(new Text("Fail"));
        assertThrowsExactly(ParsecException.class, () -> parser.parse(state));
    }
}