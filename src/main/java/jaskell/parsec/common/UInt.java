package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-07.
 * UInt 尝试将后续信息流解析成一个表示无符号整数的字符串,如果匹配失败就抛出异常.
 */
public class UInt
    implements Parsec<String, Character> {
    private final Parsec<List<Character>, Character> parser = new Many1<>(new Digit());
    @Override
    public String parse(State<Character> s)
            throws EOFException, ParsecException {
        List<Character> buffer = parser.parse(s);
        StringBuilder sb = new StringBuilder();
        buffer.forEach(sb::append);
        return sb.toString();
    }
}
