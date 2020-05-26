package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;
import java.util.Optional;

/**
 * Created by Mars Liu on 16/9/18.
 * Option accept a parsec&lt;T, E, Status, Tran&gt; and
 * return new one parsec&lt;Optional&lt;T&gt;, E, Status, Tran&gt;.
 * If the parser fail, Option parsec just return option.empty without Exception thrown.
 */
public class Option<T, E>
    implements Parsec<Optional<T>, E> {
    private final Parsec<T, E> parser;

    public Option(Parsec<T, E> parser) {
        this.parser = parser;
    }

    @Override
    public  Optional<T> parse(State<E> s)
            throws EOFException, ParsecException {
        Integer status = s.status();
        try{
            return Optional.of(parser.parse(s));
        } catch (Exception e) {
            if(status.equals(s.status())) {
                return Optional.empty();
            } else {
                throw e;
            }
        }
    }
}
