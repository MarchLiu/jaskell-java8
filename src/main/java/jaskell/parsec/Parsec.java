package jaskell.parsec;

import jaskell.util.Result;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-02.
 * Parsec defined base functions of parsec parsers.
 */
@FunctionalInterface
public interface Parsec<T, E> {
    <Status, Tran, S extends State<E, Status, Tran>> T parse(S s)
            throws EOFException, ParsecException;

    default  <Status, Tran, S extends State<E, Status, Tran>> Result<T, Exception> exec(S s){
        try {
            return new Result<>(Parsec.this.parse(s));
        } catch (Exception e) {
            return new Result<>(e);
        }
    }

    default <C> Parsec<C, E> bind(Binder<T, C, E> binder) {
        return new Parsec<C, E>() {
            @Override
            public <Status, Tran, S extends State<E, Status, Tran>> C parse(S s)
                    throws EOFException, ParsecException {
                T value = Parsec.this.parse(s);
                return binder.bind(value).parse(s);
            }
        };
    }

    default <C> Parsec<C, E> then(Parsec<C, E> parsec) {
        return new Parsec<C, E>() {
            @Override
            public <Status, Tran, S extends State<E, Status, Tran>> C parse(S s)
                    throws EOFException, ParsecException {
                Parsec.this.parse(s);
                return parsec.parse(s);
            }
        };
    }

    default <C> Parsec<T, E> over(Parsec<C, E> parsec) {
        return new Parsec<T, E>() {
            @Override
            public <Status, Tran, S extends State<E, Status, Tran>> T parse(S s)
                    throws EOFException, ParsecException {
                T value = Parsec.this.parse(s);
                parsec.parse(s);
                return value;
            }
        };
    }
}