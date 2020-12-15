package jaskell.parsec.common;

import static jaskell.parsec.common.Txt.uinteger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import jaskell.parsec.ParsecException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UIntTest extends Base {
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void simple() throws Throwable {
        State<Character> state = newState("23413214");

        UInt uint = uinteger();

        String s = uint.parse(state);

        assertEquals(s,"23413214");
    }

    @Test
    public void stop() throws Throwable {
        State<Character> state = newState("23413a214");

        UInt uint = uinteger();

        String s = uint.parse(state);

        assertEquals(s,"23413");
    }

    @Test
    public void fail() throws Throwable {
        State<Character> state = newState("x2344");

        UInt uint = new UInt();

        try {
            String s = uint.parse(state);
            throw new Exception("Expect fail when no digit at start.");
        }catch (ParsecException e) {
            assertTrue(true);
        }
    }
}
