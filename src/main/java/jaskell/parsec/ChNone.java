package jaskell.parsec;

import java.io.EOFException;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.toSet;

/**
 * Created by Mars Liu on 2016-01-10.
 * ChNone 即 char none of,是为 Character 特化的 none of
 */
public class ChNone<Status, Tran>
    implements Parsec<Character, Character, Status, Tran> {
    private final NoneOf<Character, Status, Tran> noneOf;

    @Override
    public Character parse(State<Character, Status, Tran> s)
            throws EOFException, ParsecException {
        return noneOf.parse(s);
    }

    public ChNone(String data){
        this.noneOf = new NoneOf<>(
                IntStream.range(0, data.length()).mapToObj(data::charAt).collect(toSet()));
    }
}
