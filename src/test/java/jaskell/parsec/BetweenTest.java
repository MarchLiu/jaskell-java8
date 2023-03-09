package jaskell.parsec;

import org.junit.jupiter.api.Test;

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
    public void simple() throws Exception {

        State<Character, Integer, Integer> state = newState("hello");


        Between<Character, Character, Character, Character, Integer, Integer> bmw = new Between<>(
                new Eq<>('h'),
                new Eq<>('l'),
                new Eq<>('e')
        );

        Character c = bmw.parse(state);
        assertEquals('e', (char) c);
    }

    @Test
    public void brackets() throws Exception {

        State<Character, Integer, Integer> state = newState("[hello]");


        Parsec<Character, String, Integer, Integer> parser = new Between<>(
                new Ch<Integer, Integer>('['),
                new Ch<>(']'),
                new Many1<>(new Ne<>(']'))
        ).bind(new JoinText<>());

        String re = parser.parse(state);
        assertEquals("hello", re);
    }


} 
