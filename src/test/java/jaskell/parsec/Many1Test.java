package jaskell.parsec;

import org.junit.jupiter.api.Test;

import static jaskell.parsec.Txt.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

/**
 * Many1 Tester.
 *
 * @author Mars Liu
 */
public class Many1Test extends Base {

    @Test
    public void fail() throws Exception {
        State<Character, Integer, Integer> state = newState("ello");

        Many1<Character, Character, Integer, Integer> m = new Many1<>(
                new Eq<>('h')
        );

        assertThrowsExactly(ParsecException.class,
                () -> m.parse(state));
    }

    @Test
    public void one() throws Exception {
        Parsec<Character, String, Integer, Integer> m =
                new Many1<Character, Character, Integer, Integer>(new Eq<>('h')).bind(joining());
        State<Character, Integer, Integer> state1 = newState("hello");
        String re = m.parse(state1);
        assertEquals("h", re);
    }

    @Test
    public void all() throws Exception {
        Parsec<Character, String, Integer, Integer> parser =
                new Many1<Character, Character, Integer, Integer>(new One<>()).bind(joining());
        State<Character, Integer, Integer> state1 = newState("hello");
        String re = parser.parse(state1);
        assertEquals("hello", re);
    }
} 
