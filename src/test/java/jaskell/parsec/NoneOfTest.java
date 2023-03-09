package jaskell.parsec;

import org.junit.jupiter.api.Test;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;


import java.util.stream.Stream;

public class NoneOfTest extends Base {


    /**
     * Method: script(State<T> s)
     */
    @Test
    public void simpleOK() throws Exception {
        State<Character, Integer, Integer> state = newState("hello");

        NoneOf<Character, Integer, Integer> noneOf = new NoneOf<>(Stream.of('k', 'o', 'f').collect(toSet()));
        Character c = noneOf.parse(state);

        assertEquals(c, new Character('h'));
    }

    @Test
    public void simpleFail() throws Exception {
        NoneOf<Character, Integer, Integer> noneOf = new NoneOf<>(Stream.of('k', 'f', 's').collect(toSet()));

        String content = "sound";
        State<Character, Integer, Integer> state2 = newState(content);
        assertThrowsExactly(ParsecException.class,
                () -> noneOf.parse(state2),
                String.format("Expect none of \"%s\" in \"sound\" failed ", "kfs")
        );

    }


} 
