package jaskell.parsec;

import java.io.EOFException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mars Liu on 2016/9/25.
 */

public class JoinCharacters<Status, Tran>
    implements Binder<Character, List<Character>, String, Status, Tran> {
    public Parsec<Character, String, Status, Tran> bind(List<Character> value) {
        return state -> {
            StringBuilder sb = new StringBuilder();
            value.forEach(sb::append);
            return sb.toString();
        };
    }
}