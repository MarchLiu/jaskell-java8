package jaskell.parsec.common;

import static jaskell.parsec.common.Txt.joining;
import static jaskell.parsec.common.Atom.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import jaskell.parsec.ParsecException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Many1 Tester.
 *
 * @author Mars Liu
 */
public class Many1Test extends Base {


    @Test
    public void fail() throws Exception {
        State<Character> state = newState("ello");

        Many1<Character, Character> m = new Many1<>(
                eq('h')
        );

        assertThrowsExactly(ParsecException.class,
                () -> m.parse(state));
    }

    @Test
    public void one() throws Exception {
        Parsec<Character, String> m =
                new Many1<>(new Eq<>('h')).bind(joining());
        State<Character> state1 = newState("hello");
        String re = m.parse(state1);
        assertEquals("h", re);
    }

    @Test
    public void all() throws Exception {
        Parsec<Character, String> parser =
                new Many1<>(new One<Character>()).bind(joining());
        State<Character> state1 = newState("hello");
        String re = parser.parse(state1);
        assertEquals("hello", re);
    }
} 
