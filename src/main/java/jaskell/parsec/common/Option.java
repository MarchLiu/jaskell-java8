package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;
import java.util.Optional;

/**
 * Created by Mars Liu on 16/9/18.
 * Option accept a parsec&lt;E, T, Status, Tran&gt; and
 * return new one parsec&lt;Optional&lt;T&gt;, E, Status, Tran&gt;.
 * If the parser fail, Option parsec just return option.empty without Exception thrown.
 */
public class Option<E, T>
    implements Parsec<E, Optional<T>> {
    private final Parsec<E, T> parser;

    public Option(Parsec<E, T> parser) {
        this.parser = new Attempt<>(parser);
    }

    @Override
    public  Optional<T> parse(State<E> s)
            throws Exception {
        Integer tran = s.begin();
        try{
            Optional<T> result = Optional.of(parser.parse(s));
            s.commit(tran);
            return result;
        } catch (Exception e) {
            s.rollback(tran);
            return Optional.empty();
        }
    }
}
