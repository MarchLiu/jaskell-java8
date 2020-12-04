package jaskell.parsec;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-07.
 * Int 算子尝试将后续的信息项组成一个整数,如果获得的信息不足以组成一个整数,抛出异常.
 */
public class Int<Status, Tran>
    implements Parsec<Character, String, Status, Tran> {
    private final Parsec<Character, List<Character>, Status, Tran> parser =
         s -> {
          List<Character> re = new ArrayList<>();
          Option<Character, Character, Status, Tran> sign = new Option<>(new Try<>(new Ch<>('-')));
          sign.parse(s).ifPresent(re::add);
          re.addAll(new Many1<Character, Character, Status, Tran>(new Digit<>()).parse(s));
          return re;
        };

    @Override
    public String parse(State<Character, Status, Tran> s)
            throws EOFException, ParsecException {
        List<Character> buffer = parser.parse(s);
        StringBuilder sb = new StringBuilder();
        buffer.forEach(sb::append);
        return sb.toString();
    }
}
