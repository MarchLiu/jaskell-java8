package jaskell.parsec;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-07.
 * UDecimal 尝试将后续信息流解析成一个表示无符号浮点数的字符串,如果匹配失败就抛出异常.
 */
public class UDecimal<Status, Tran>
    implements Parsec<String, Character, Status, Tran> {
    private final Parsec<List<Character>, Character, Status, Tran> parser =
            new Choice<List<Character>, Character, Status, Tran>(new Try<>(new Many1<>(new Digit<>())),
                new Return<>(new ArrayList<>('0'))).over(
            new Ch<>('.')).bind(
                (List<Character> value) -> s -> {
                value.add('.');
                value.addAll(new Many1<>(new Digit<Status, Tran>()).parse(s));
                return value;
            });

    @Override
    public String parse(State<Character, Status, Tran> s)
            throws EOFException, ParsecException {
        List<Character> buffer = parser.parse(s);
        StringBuilder sb = new StringBuilder();
        buffer.forEach(sb::append);
        return sb.toString();
    }
}
