package jaskell.parsec.common;

import static java.util.stream.Collectors.toSet;

import jaskell.parsec.ParsecException;

import java.io.EOFException;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Created by Mars Liu on 2016-01-10.
 * ChIn 即 char in ,是为 Character 特化的 one of
 */
public class ChIn implements
    Parsec<Character, Character> {
    private final OneOf<Character> oneOf;

    @Override
    public Character parse(State<Character> s)
            throws EOFException, ParsecException {
        return oneOf.parse(s);
    }

    public ChIn(String data){
        Set<Character> buffer = IntStream.range(0, data.length())
                .mapToObj(data::charAt).collect(toSet());
        this.oneOf = new OneOf<>(buffer);
    }
}
