package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-07.
 * Int 算子尝试将后续的信息项组成一个整数,如果获得的信息不足以组成一个整数,抛出异常.
 */
public class Int implements Parsec<Character, String> {
    private final Parsec<Character, List<Character>> parser =
        s -> {
          List<Character> re = new ArrayList<>();
          Option<Character, Character> sign = new Option<>(new Attempt<>(new Ch('-')));
          sign.parse(s).ifPresent(re::add);
          re.addAll(new Many1<>(new Digit()).parse(s));
          return re;
        };

    @Override
    public String parse(State<Character> s)
            throws Exception {
        List<Character> buffer = parser.parse(s);
        StringBuilder sb = new StringBuilder();
        buffer.forEach(sb::append);
        return sb.toString();
    }
}
