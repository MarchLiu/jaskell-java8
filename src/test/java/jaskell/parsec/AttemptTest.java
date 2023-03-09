package jaskell.parsec;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AttemptTest extends Base {

    @Test
    public void simple() throws Exception {
        List<String> data = Arrays.asList("Hello", "World");

        State<String, Integer, Integer> state = new SimpleState<>(data);
        Integer idx = state.status();
        Attempt<String, String, Integer, Integer> attemptIt = new Attempt<>(new Eq<>("Hello"));

        String re = attemptIt.parse(state);

        assertEquals(re, "Hello");
        assertNotEquals(idx, state.status());
    }

    @Test
    public void rollback() throws Exception {
        List<String> data = Arrays.asList("Hello", "World");
        SimpleState<String> state = new SimpleState<>(data);
        Integer idx = state.status();
        Attempt<String, String, Integer, Integer> attemptIt = new Attempt<>(new Eq<>("hello"));

        assertThrowsExactly(ParsecException.class,
                () -> attemptIt.parse(state),
                "Expect a error for Hello.");
        assertEquals(idx, state.status());
    }
}
