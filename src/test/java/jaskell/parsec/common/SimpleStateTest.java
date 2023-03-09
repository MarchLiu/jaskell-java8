package jaskell.parsec.common;

import jaskell.parsec.ParsecException;
import jaskell.parsec.State;
import org.junit.jupiter.api.Test;

import java.io.EOFException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

/**
 * BasicState Tester.
 *
 * @author Mars Liu
 * @version 1.0
 * @since 2016-09-11
 */
public class SimpleStateTest extends Base {


    /**
     * Method: Index()
     */
    @Test
    public void testIndex() throws Exception {
        String data = "It is a \"string\" for this unit test";
        jaskell.parsec.State<Character, Integer, Integer> state = newState(data);
        while (state.status() < data.length()) {
            int index = state.status();
            Character c = state.next();
            Character chr = data.charAt(index);
            assertEquals(chr, c);
        }
        assertThrowsExactly(
                EOFException.class,
                state::next,
                "The state next at preview line should failed");

    }

    /**
     * Method: Begin()
     */
    @Test
    public void testBegin() throws Exception {
        jaskell.parsec.State<Character, Integer, Integer> state = newState("hello");

        Character c = state.next();

        assertEquals(new Character('h'), c);

        Integer a = state.begin();

        state.next();
        state.next();
        state.next();

        state.rollback(a);

        Character d = state.next();

        assertEquals(new Character('e'), d);
    }

    /**
     * Method: Commit(int tran)
     */
    @Test
    public void testCommit() throws Exception {
        jaskell.parsec.State<Character, Integer, Integer> state = newState("hello");
        int a = state.begin();
        Character c = state.next();


        assertEquals(new Character('h'), c);
        state.next();

        state.commit(a);

        Character d = state.next();

        assertEquals(new Character('l'), d);
    }

    /**
     * Method: Rollback(int tran)
     */
    @Test
    public void testRollback() throws Exception {
        jaskell.parsec.State<Character, Integer, Integer> state = newState("hello");

        int a = state.begin();
        Character c = state.next();


        assertEquals(new Character('h'), c);

        state.rollback(a);

        Character d = state.next();

        assertEquals(new Character('h'), d);
    }

    /**
     * Method: Next()
     */
    @Test
    public void testNext() throws Exception {
        State<Character, Integer, Integer> state = newState("hello");


        Character c = state.next();
        assertEquals(new Character('h'), c);

        Character d = state.next();
        assertEquals(new Character('e'), d);

        Character e = state.next();
        assertEquals(new Character('l'),e);

        Character f = state.next();
        assertEquals(new Character('l'), f);

        Character g = state.next();
        assertEquals(new Character('o'), g);

    }

} 
