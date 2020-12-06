package jaskell.parsec.common;

import static jaskell.parsec.common.Atom.eq;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AttemptTest extends Base {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void simple() throws Exception {
        List<String> data = Arrays.asList("Hello", "World");

        State<String> state = new SimpleState<>(data);
        Integer idx = state.status();
        Attempt<String, String> attemptIt = new Attempt<>(eq("Hello"));

        String re = attemptIt.parse(state);

        assertEquals(re, "Hello");
        assertNotEquals(idx, state.status());
    }
    @Test
    public void rollback() throws Exception {
        List<String> data = Arrays.asList("Hello", "World");
        SimpleState<String> state = new SimpleState<>(data);
        Integer idx = state.status();
        Attempt<String, String> attemptIt = new Attempt<>(new Eq<>("hello"));

        try{
            String re = attemptIt.parse(state);
            fail("Expect a error for Hello.");
        }catch(Exception e){
            assertEquals(idx, state.status());
        }
    }
}
