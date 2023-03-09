package jaskell.parsec.common;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReturnTest extends Base {
    /**
     * Method: script(State<E> s)
     */
    @Test
    public void simple() throws Exception {
        State<Character> state = newState("Hello World");
        Return<Character, BigDecimal> returns = new Return<>(new BigDecimal("3.1415926"));
        Integer idx = state.status();
        BigDecimal re = returns.parse(state);
        assertEquals(new BigDecimal("3.1415926"), re);
        assertEquals(idx, state.status());
    }
}
