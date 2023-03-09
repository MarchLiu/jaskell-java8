package jaskell.parsec;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UIntTest extends Base {

    @Test
    public void simple() throws Exception {
        State<Character, Integer, Integer> state = newState("23413214");

        UInt<Integer, Integer> uint = new UInt<>();

        String s = uint.parse(state);

        assertEquals("23413214", s);
    }

    @Test
    public void stop() throws Exception {
        State<Character, Integer, Integer> state = newState("23413a214");

        UInt<Integer, Integer> uint = new UInt<>();

        String s = uint.parse(state);

        assertEquals("23413", s);
    }

    @Test
    public void fail() throws Exception {
        State<Character, Integer, Integer> state = newState("x2344");

        UInt<Integer, Integer> uint = new UInt<>();

        assertThrowsExactly(ParsecException.class,
                () -> uint.parse(state),
                "Expect fail when no digit at start.");
    }
}
