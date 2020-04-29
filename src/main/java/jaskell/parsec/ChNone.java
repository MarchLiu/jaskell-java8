package jaskell.parsec;

import java.io.EOFException;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.toSet;

/**
 * Created by Mars Liu on 2016-01-10.
 * ChNone 即 char none of,是为 Character 特化的 none of
 */
public class ChNone implements Parsec<Character, Character> {
    private NoneOf<Character> noneOf;

    @Override
    public <Status, Tran, S extends State<Character, Status, Tran>> Character parse(S s)
            throws EOFException, ParsecException {
        return noneOf.parse(s);
    }

    public ChNone(String data){
        this.noneOf = new NoneOf<>(
                IntStream.range(0, data.length()).mapToObj(data::charAt).collect(toSet()));
    }
}
