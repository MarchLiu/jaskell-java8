package jaskell.parsec;

import org.junit.jupiter.api.Test;

import java.io.EOFException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * BasicState Tester.
 *
 * @author Mars Liu
 * @since 2016-09-11
 * @version 1.0
 */
public class SimpleStateTest extends Base {

    /**
     *
     * Method: Index()
     *
     */
    @Test
    public void testIndex() throws Exception {
        String data = "It is a \"string\" for this unit test";
        State<Character, Integer, Integer> state = newState(data);
        while (state.status()< data.length()){
            int index = state.status();
            Character c = state.next();
            Character chr = data.charAt(index);
            assertEquals(c, chr);
        }
        try{
            Character failed = state.next();
            String message = String.format("The state next at preview line should failed but %c.", failed);
            fail(message);
        } catch (EOFException e) {
            assertSame(e.getClass(), EOFException.class, "the error is Eof");
        }
    }

    /**
     *
     * Method: Begin()
     *
     */
    @Test
    public void testBegin() throws Exception {
        State<Character, Integer, Integer> state = newState("hello");

        Character c = state.next();



        assertEquals(c,new Character('h'));

        Integer a = state.begin();

        state.next();
        state.next();
        state.next();

        state.rollback(a);

        Character d = state.next();

        assertEquals(d,new Character('e'));
    }

    /**
     *
     * Method: Commit(int tran)
     *
     */
    @Test
    public void testCommit() throws Exception {
        State<Character, Integer, Integer> state = newState("hello");
        int a = state.begin();
        Character c = state.next();


        assertEquals(c,new Character('h'));
        state.next();

        state.commit(a);

        Character d = state.next();

        assertEquals(d,new Character('l'));
    }

    /**
     *
     * Method: Rollback(int tran)
     *
     */
    @Test
    public void testRollback() throws Exception {
        State<Character, Integer, Integer> state = newState("hello");

        int a = state.begin();
        Character c = state.next();


        assertEquals(c,new Character('h'));

        state.rollback(a);

        Character d = state.next();

        assertEquals(d,new Character('h'));
    }

    /**
     *
     * Method: Next()
     *
     */
    @Test
    public void testNext() throws Exception {
        State<Character, Integer, Integer> state = newState("hello");


        Character c = state.next();

        assertEquals(c, new Character('h'));

        Character d = state.next();

        assertEquals(d,new Character('e'));
        Character e = state.next();

        assertEquals(e,new Character('l'));
        Character f = state.next();

        assertEquals(f,new Character('l'));
        Character g = state.next();

        assertEquals(g,new Character('o'));

    }


} 
