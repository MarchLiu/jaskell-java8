package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by Mars Liu .
 * Newline 尝试匹配换\n
 * -----------------
 */
public class Newline implements Parsec<Character, Character> {
    private Parsec<Character, Character> parser = new Ch('\n');
    @Override
    public <Status, Tran, S extends State<Character, Status, Tran>> Character parse(S s)
            throws EOFException, ParsecException {
        return parser.parse(s);
    }
}
