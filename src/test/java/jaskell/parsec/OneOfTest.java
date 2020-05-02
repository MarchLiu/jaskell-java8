package jaskell.parsec;

import static java.util.stream.Collectors.toSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Stream;

public class OneOfTest extends Base {
    private String data = "It is a \"string\" for this unit test";


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
    public void simple() throws Exception {
        State<Character, Integer, Integer> state = newState("hello");

        Set<Character> buffer = Stream.of('b', 'e', 'h', 'f').collect(toSet()); //IntStream.range(0, 2).mapToObj(data::charAt).collect(toList());
        OneOf<Character, Integer, Integer> oneOf = new OneOf<>(buffer);

        Character c = oneOf.parse(state);


        Assert.assertEquals(c, new Character('h'));
    }

    @Test
    public void fail() throws Exception {
        State<Character, Integer, Integer> state = newState("hello");
        OneOf<Character, Integer, Integer> oneOf = new OneOf<>(Stream.of('d', 'a', 't', 'e').collect(toSet()));
        try{
            Character d = oneOf.parse(state);
            String message = String.format("Expect none char in %s but %c", "date", d);
            Assert.fail(message);
        }catch (ParsecException e){
            Assert.assertTrue(true);
        }
    }
}
