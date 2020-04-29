package jaskell.parsec;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-08.
 * Decimal 尝试将后续的信息解析为 Decimal ,直到第一个无效信息为止.如果获取的信息不足以组成一个有效的浮点数,抛出异常.
 */
public class Decimal implements Parsec<String, Character> {
    private Parsec<List<Character>, Character> parser =
            new Choice<>(new Try<>(new Ch('-').then(new Return<>(
                    new ArrayList<>('-')))),
                    new Return<List<Character>, Character>(new ArrayList<>()))
                    .bind(value -> new Parsec<List<Character>, Character>() {
                        @Override
                        public <Status, Tran, S extends State<Character, Status, Tran>> List<Character> parse(S s)
                                throws EOFException, ParsecException {
                            Parsec<List<Character>, Character> numbers = new Many<>(new Digit());
                            List<Character> left = numbers.parse(s);
                            if (left.isEmpty()) {
                                value.add('0');
                            } else {
                                value.addAll(left);
                            }
                            value.add(new Ch('.').parse(s));
                            // 下面两行相当于一次 Many1
                            value.add(new Digit().parse(s));
                            value.addAll(numbers.parse(s));
                            return value;
                        }
                    });

    @Override
    public <Status, Tran, S extends State<Character, Status, Tran>> String parse(S s)
            throws EOFException, ParsecException {
        List<Character> buffer = parser.parse(s);
        StringBuilder sb = new StringBuilder();
        buffer.forEach(sb::append);
        return sb.toString();
    }
}
