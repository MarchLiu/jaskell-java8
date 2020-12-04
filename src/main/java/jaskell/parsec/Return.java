package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-07.
 * Return 不改变 state ,它直接返回预设值.
 */
public class Return<E, T, Status, Tran> implements Parsec<E, T, Status, Tran> {
    private final T item;

    @Override
    public T parse(State<E, Status, Tran> s)
            throws EOFException, ParsecException {
        return item;
    }

    public Return(T item){
        this.item = item;
    }
}
