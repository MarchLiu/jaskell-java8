package jaskell.parsec;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-07.
 * Int 算子尝试将后续的信息项组成一个整数,如果获得的信息不足以组成一个整数,抛出异常.
 */
public class Int implements Parsec<String, Character> {
    private Parsec<List<Character>, Character> parser = new Ch('-').then(
            new Parsec<List<Character>, Character>() {
                @Override
                public <Status, Tran, S extends State<Character, Status, Tran>> List<Character> parse(S s)
                        throws EOFException, ParsecException {
                    List<Character> re = new ArrayList<>('-');
                    re.addAll(new Many1<>(new Digit()).parse(s));
                    return re;
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
