package jaskell.parsec.common;

import static jaskell.parsec.common.Txt.text;

import jaskell.parsec.ParsecException;

import java.io.EOFException;

/**
 * Created by zhaonf on 16/1/10.
 * EndOfLine 尝试匹配 \r\n 或 \n
 */
public class EndOfLine
    implements Parsec<String, Character> {
    private final Parsec<String, Character> parsec =
        new Choice<>(new Text("\n"), new Text("\r\n"));
    @Override
    public String parse(State<Character> s)
            throws EOFException, ParsecException {
        return parsec.parse(s);
    }
}
