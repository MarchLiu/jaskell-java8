package jaskell.parsec;

import java.io.EOFException;
import java.util.Optional;

/**
 * Created by Mars Liu on 16/9/18.
 * Option accept a parsec&lt;T, E, Status, Tran&gt; and
 * return new one parsec&lt;Optional&lt;T&gt;, E, Status, Tran&gt;.
 * If the parser fail, Option parsec just return option.empty without Exception thrown.
 */
public class Option<T, E> implements Parsec<Optional<T>, E> {
    private Parsec<T, E> parser;

    public Option(Parsec<T, E> parser) {
        this.parser = parser;
    }

    @Override
    public <Status, Tran, S extends State<E, Status, Tran>>  Optional<T> parse(S s)
            throws EOFException, ParsecException {
        try{
            return Optional.of(parser.parse(s));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
