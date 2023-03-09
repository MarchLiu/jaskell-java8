package jaskell.parsec.common;

import org.junit.jupiter.api.Test;

import static jaskell.parsec.common.Txt.ch;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class NewlineTest extends Base {

    @Test
    public void simpleCrlf() throws Throwable {
        State<Character> state = newState("\r\n");
        Parsec<Character, String> crlf = new Crlf();

        String re = crlf.parse(state);
        assertEquals("\r\n", re);
    }

    /**
     * Method: script(State<E> s)
     */
    @Test
    public void simpleNl() throws Throwable {
        State<Character> state = newState("\r\n");

        Parsec<Character, Character> enter = new Newline();

        Character c = ch('\r').then(enter).parse(state);

        assertEquals('\n', c.charValue());
    }
}
