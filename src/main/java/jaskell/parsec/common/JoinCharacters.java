package jaskell.parsec.common;

import java.util.List;

/**
 * Created by Mars Liu on 2016/9/25.
 */

public class JoinCharacters
    implements Binder<Character, List<Character>, String> {
    public Parsec<Character, String> bind(List<Character> value) {
        return state -> {
            StringBuilder sb = new StringBuilder();
            value.forEach(sb::append);
            return sb.toString();
        };
    }
}