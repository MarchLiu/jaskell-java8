package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by zhaonf on 16/1/10.
 * EndOfLine 尝试匹配 \n\r 或 \n
 */
public class EndOfLine implements Parsec<String, Character> {
    @Override
    public <Status, Tran, S extends State<Character, Status, Tran>> String parse(S s)
            throws EOFException, ParsecException {
        new Ch('\n').parse(s);
        try{
            new Try<>(new Ch('\r')).parse(s);
            return "\n\r";
        } catch (EOFException|ParsecException e) {
            return "\n";
        }
    }
}
