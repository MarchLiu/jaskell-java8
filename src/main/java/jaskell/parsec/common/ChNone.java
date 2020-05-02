package jaskell.parsec.common;

import static java.util.stream.Collectors.toSet;

import jaskell.parsec.ParsecException;

import java.io.EOFException;
import java.util.stream.IntStream;

/**
 * Created by Mars Liu on 2016-01-10.
 * ChNone 即 char none of,是为 Character 特化的 none of
 */
public class ChNone implements Parsec<Character, Character> {
    private final NoneOf<Character> noneOf;

    @Override
    public Character parse(State<Character> s)
            throws EOFException, ParsecException {
        return noneOf.parse(s);
    }

    public ChNone(String data){
        this.noneOf = new NoneOf<>(
                IntStream.range(0, data.length()).mapToObj(data::charAt).collect(toSet()));
    }
}
