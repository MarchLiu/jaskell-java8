package jaskell.parsec.common;

import jaskell.parsec.ParsecException;
import org.junit.jupiter.api.Test;

import static jaskell.parsec.common.Atom.eq;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class AttemptTest extends Base {

    @Test
    public void simple() throws Exception {
        List<String> data = Arrays.asList("Hello", "World");

        State<String> state = new SimpleState<>(data);
        Integer idx = state.status();
        Attempt<String, String> attemptIt = new Attempt<>(eq("Hello"));

        String re = attemptIt.parse(state);

        assertEquals("Hello", re);
        assertNotEquals(idx, state.status());
    }

    @Test
    public void rollback() {
        List<String> data = Arrays.asList("Hello", "World");
        SimpleState<String> state = new SimpleState<>(data);
        Integer idx = state.status();
        Attempt<String, String> attemptIt = new Attempt<>(new Eq<>("hello"));

        assertThrowsExactly(ParsecException.class,
                () -> attemptIt.parse(state),
                "Expect a error for Hello.");

    }
}
