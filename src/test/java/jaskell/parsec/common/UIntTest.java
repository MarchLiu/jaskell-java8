package jaskell.parsec.common;

import static jaskell.parsec.common.Txt.uinteger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import jaskell.parsec.ParsecException;
import org.junit.jupiter.api.Test;

public class UIntTest extends Base {

    @Test
    public void simple() throws Throwable {
        State<Character> state = newState("23413214");

        UInt uint = uinteger();

        String s = uint.parse(state);

        assertEquals("23413214", s);
    }

    @Test
    public void stop() throws Throwable {
        State<Character> state = newState("23413a214");

        UInt uint = uinteger();

        String s = uint.parse(state);

        assertEquals("23413", s);
    }

    @Test
    public void fail() throws Throwable {
        State<Character> state = newState("x2344");

        UInt uint = new UInt();

        assertThrowsExactly(ParsecException.class,
                () -> uint.parse(state),
                "Expect fail when no digit at start.");

    }
}
