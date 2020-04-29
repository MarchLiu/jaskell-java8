package jaskell.parsec;

import java.io.EOFException;
import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * Created by Mars Liu on 16/9/13.
 * JoinText is a binder. It join Character List to String.
 */
public class JoinText<E> implements Binder<List<E>, String, E> {
    @Override
    public Parsec<String, E> bind(List<E> value) {
        return new Parsec<String, E>() {
            @Override
            public <Status, Tran, S extends State<E, Status, Tran>> String parse(S state)
                    throws EOFException, ParsecException {
                return value.stream().map(E::toString).collect(joining());
            }
        };
    }
}

