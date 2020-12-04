package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;

/**
 * Created by Mars Liu on 16/9/15.
 * Ahead look forward state and try to match parser.
 * Ahead return the parser data or fail and rollback state whatever
 */
public class Ahead<E, T>
    implements Parsec<E, T> {
    Parsec<E, T> parser;

    @Override
    public T parse(State<E> s) throws EOFException, ParsecException {
        Integer tran = s.begin();
        try {
            return parser.parse(s);
        }  finally {
            s.rollback(tran);
        }
    }

    public Ahead(Parsec<E, T> parser){
        this.parser = parser;
    }
}
