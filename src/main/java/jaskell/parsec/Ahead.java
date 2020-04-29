package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by Mars Liu on 16/9/15.
 * Ahead look forward state and try to match parser.
 * Ahead return the parser data or fail and rollback state whatever
 */
public class Ahead<T, E> implements Parsec<T, E> {
    Parsec<T, E> parser;

    @Override
    public <Status, Tran, S extends State<E, Status, Tran>> T parse(S s) throws EOFException, ParsecException {
        Tran tran = s.begin();
        try {
            return parser.parse(s);
        }  finally {
            s.rollback(tran);
        }
    }

    public Ahead(Parsec<T, E> parser){
        this.parser = parser;
    }
}
