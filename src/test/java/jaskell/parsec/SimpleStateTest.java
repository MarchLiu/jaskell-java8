package jaskell.parsec;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.EOFException;

/**
 * BasicState Tester.
 *
 * @author Mars Liu
 * @since 2016-09-11
 * @version 1.0
 */
public class SimpleStateTest extends Base {


    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {
    }
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
            Assert.assertEquals(c, chr);
        }
        try{
            Character failed = state.next();
            String message = String.format("The state next at preview line should failed but %c.", failed);
            Assert.fail(message);
        } catch (EOFException e) {
            Assert.assertSame("the error is Eof", EOFException.class, e.getClass());
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



        Assert.assertEquals(c,new Character('h'));

        Integer a = state.begin();

        state.next();
        state.next();
        state.next();

        state.rollback(a);

        Character d = state.next();

        Assert.assertEquals(d,new Character('e'));
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


        Assert.assertEquals(c,new Character('h'));
        state.next();

        state.commit(a);

        Character d = state.next();

        Assert.assertEquals(d,new Character('l'));
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


        Assert.assertEquals(c,new Character('h'));

        state.rollback(a);

        Character d = state.next();

        Assert.assertEquals(d,new Character('h'));
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

        Assert.assertEquals(c, new Character('h'));

        Character d = state.next();

        Assert.assertEquals(d,new Character('e'));
        Character e = state.next();

        Assert.assertEquals(e,new Character('l'));
        Character f = state.next();

        Assert.assertEquals(f,new Character('l'));
        Character g = state.next();

        Assert.assertEquals(g,new Character('o'));

    }


} 
