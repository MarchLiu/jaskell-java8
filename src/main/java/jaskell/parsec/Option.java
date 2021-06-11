package jaskell.parsec;

import java.io.EOFException;
import java.util.Optional;

/**
 * Created by Mars Liu on 16/9/18.
 * Option accept a parsec&lt;E, T, Status, Tran&gt; and
 * return new one parsec&lt;Optional&lt;T&gt;, E, Status, Tran&gt;.
 * If the parser fail, Option parsec just return option.empty without Exception thrown.
 */
public class Option<E, T, Status, Tran>
    implements Parsec<E, Optional<T>, Status, Tran> {
    private final Parsec<E, T, Status, Tran> parser;

    public Option(Parsec<E, T, Status, Tran> parser) {
        this.parser = parser;
    }

    @Override
    public  Optional<T> parse(State<E, Status, Tran> s)
            throws EOFException, ParsecException {
        Tran tran = s.begin();
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
