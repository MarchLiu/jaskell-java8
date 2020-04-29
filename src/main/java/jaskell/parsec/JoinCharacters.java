package jaskell.parsec;

import java.io.EOFException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mars Liu on 2016/9/25.
 */

public class JoinCharacters implements Binder<List<Character>, String, Character> {
    public Parsec<String, Character> bind(List<Character> value) {
        return new Parsec<String, Character>() {
            @Override
            public <Status, Tran, S extends State<Character, Status, Tran>> String parse(S state)
                    throws EOFException, ParsecException {
                StringBuilder sb = new StringBuilder();
                value.forEach(sb::append);
                return sb.toString();
            }
        };
    }
}