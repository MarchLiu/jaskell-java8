package jaskell.parsec;

import java.io.EOFException;
import java.util.Set;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.toSet;

/**
 * Created by Mars Liu on 2016-01-10.
 * ChIn 即 char in ,是为 Character 特化的 one of
 */
public class ChIn implements Parsec<Character, Character> {
    private OneOf<Character> oneOf;

    @Override
    public <Status, Tran, S extends State<Character, Status, Tran>> Character parse(S s)
            throws EOFException, ParsecException {
        return oneOf.parse(s);
    }

    public ChIn(String data){
        Set<Character> buffer = IntStream.range(0, data.length())
                .mapToObj(data::charAt).collect(toSet());
        this.oneOf = new OneOf<>(buffer);
    }
}
