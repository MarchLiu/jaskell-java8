package jaskell.parsec;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ManyTil Tester.
 *
 * @author Mars Liu
 * @since 2016-09-12
 */
public class ManyTillTest extends Base {


    /**
     * Method: script(State<E> s)
     */
    @Test
    public void TestManyTill() throws Exception {
        State<Character, Integer, Integer> state = newState("hhhhhhlhhhll");

        ManyTill<Character, Character, Character, Integer, Integer> m = new ManyTill<>(
                new Eq<>('h'),
                new Eq<>('l')
        );

        List<Character> a = m.parse(state);
        assertEquals(a.size(), 6);
    }


} 
