package jaskell.parsec;

import org.junit.jupiter.api.Test;

import static jaskell.parsec.Txt.ch;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class NewlineTest extends Base {

    @Test
    public void simpleCrlf() throws Exception {
        State<Character, Integer, Integer> state = newState("\r\n");
        Parsec<Character, String, Integer, Integer> crlf = new Crlf<>();

        String re = crlf.parse(state);
        assertEquals("\r\n", re);
    }

    /**
     * Method: script(State<E> s)
     */
    @Test
    public void simpleNl() throws Exception {
        State<Character, Integer, Integer> state = newState("\r\n");

        Parsec<Character, Character, Integer, Integer> enter = new Newline<>();

        Character c = new Ch<Integer, Integer>('\r').then(enter).parse(state);

        assertEquals(c.charValue(), '\n');
    }
}
