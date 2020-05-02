package jaskell.parsec;

import java.io.EOFException;
import java.util.Optional;

/**
 * Created by Mars Liu on 16/9/18.
 * Option accept a parsec&lt;T, E, Status, Tran&gt; and
 * return new one parsec&lt;Optional&lt;T&gt;, E, Status, Tran&gt;.
 * If the parser fail, Option parsec just return option.empty without Exception thrown.
 */
public class Option<T, E, Status, Tran>
    implements Parsec<Optional<T>, E, Status, Tran> {
    private final Parsec<T, E, Status, Tran> parser;

    public Option(Parsec<T, E, Status, Tran> parser) {
        this.parser = parser;
    }

    @Override
    public  Optional<T> parse(State<E, Status, Tran> s)
            throws EOFException, ParsecException {
        try{
            return Optional.of(parser.parse(s));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
