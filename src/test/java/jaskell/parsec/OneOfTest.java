package jaskell.parsec;

import org.junit.jupiter.api.Test;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.Set;
import java.util.stream.Stream;

public class OneOfTest extends Base {
    private final String data = "It is a \"string\" for this unit test";


    /**
     * Method: script(State<T> s)
     */
    @Test
    public void simple() throws Exception {
        State<Character, Integer, Integer> state = newState("hello");

        Set<Character> buffer = Stream.of('b', 'e', 'h', 'f').collect(toSet()); //IntStream.range(0, 2).mapToObj(data::charAt).collect(toList());
        OneOf<Character, Integer, Integer> oneOf = new OneOf<>(buffer);

        Character c = oneOf.parse(state);


        assertEquals(new Character('h'), c);
    }

    @Test
    public void fail() throws Exception {
        State<Character, Integer, Integer> state = newState("hello");
        OneOf<Character, Integer, Integer> oneOf = new OneOf<>(Stream.of('d', 'a', 't', 'e').collect(toSet()));

        assertThrowsExactly(ParsecException.class,
                () -> oneOf.parse(state),
                String.format("Expect none match in %s", "date"));

    }
}
