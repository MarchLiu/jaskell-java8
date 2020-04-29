package jaskell.parsec;

import static java.util.stream.Collectors.toSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

public class NoneOfTest extends Base {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: script(State<T> s)
     */
    @Test
    public void simpleOK() throws Exception {
        State<Character, Integer, Integer> state = newState("hello");

        NoneOf<Character> noneOf = new NoneOf<>(Stream.of('k', 'o', 'f').collect(toSet()));
        Character c = noneOf.parse(state);

        Assert.assertEquals(c, new Character('h'));
    }

    @Test
    public void simpleFail() throws Exception {
        NoneOf<Character> noneOf = new NoneOf<>(Stream.of('k', 'f', 's').collect(toSet()));
        try {
            String content = "sound";
            State<Character, Integer, Integer> state2 = newState(content);
            Character d = noneOf.parse(state2);
            String message = String.format("Expect none of \"%s\" failed  but '%c'", "kfs", d);
            Assert.fail(message);
        } catch (ParsecException e){
            Assert.assertTrue(true);
        }
    }


} 
