package jaskell.parsec.common;

import org.junit.jupiter.api.Test;

import static jaskell.parsec.common.Atom.eq;
import static jaskell.parsec.common.Atom.ne;
import static jaskell.parsec.common.Combinator.between;
import static jaskell.parsec.common.Combinator.many;
import static jaskell.parsec.common.Txt.ch;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Between Tester.
 *
 * @author Mars Liu
 *
 *
 */
public class BetweenTest extends Base {

    /**
     * Method: script(State<E> s)
     */
    @Test
    public void simple() throws Throwable {

        State<Character> state = newState("hello");

        Parsec<Character, Character> bmw = between(
                eq('h'),
                eq('l'),
                eq('e')
        );

        Character c = bmw.parse(state);
        assertEquals('e', (char) c);
    }

    @Test
    public void brackets() throws Throwable {

        State<Character> state = newState("[hello]");


        Parsec<Character, String> parser = between(
                ch('['),
                ch(']'),
                many(ne(']'))
        ).bind(new JoinText());

        String re = parser.parse(state);
        assertEquals("hello", re);
    }


} 
