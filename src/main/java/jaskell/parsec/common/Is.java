package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;
import java.util.function.Predicate;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/23 14:58
 */
public class Is<E> implements Parsec<E, E> {
    private final Predicate<E> predicate;

    public Is(Predicate<E> predicate) {
        this.predicate = predicate;
    }

    @Override
    public E parse(State<E> s) throws EOFException, ParsecException {
        E item = s.next();
        if(predicate.test(item)){
            return item;
        } else {
            throw s.trap(String.format("expect anything pass the predicate check but %s", item));
        }
    }
}
