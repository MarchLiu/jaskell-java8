package jaskell.parsec.common;

import jaskell.parsec.ParsecException;
import org.junit.Assert;
import org.junit.Test;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/23 15:02
 */
public class IsTest {
    @Test
    public void testPassed() throws Throwable {
        Parsec<Integer, Integer> parser = new Is<>(i -> i%2 == 0);
        List<Integer> buffer = new ArrayList<>();
        buffer.add(2);
        State<Integer> state = new SimpleState<>(buffer);
        Integer result = parser.parse(state);
        Assert.assertEquals(2L, result.longValue());
    }

    @Test
    public void testNotPassed() throws EOFException {
        Parsec<Integer, Integer> parser = new Is<>(i -> i%2 == 1);
        List<Integer> buffer = new ArrayList<>();
        buffer.add(2);
        State<Integer> state = new SimpleState<>(buffer);
        try {
            Integer result = parser.parse(state);
            Assert.fail("should run it for never");
        } catch (Throwable error) {
            Assert.assertEquals(ParsecException.class, error.getClass());
            Assert.assertEquals(1L, state.status().longValue());
        }

    }
}
