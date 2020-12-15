package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-07.
 * 跳过 0 到多次重复匹配
 */
public class Skip<E, T>
    implements Parsec<E, T> {
    private final Parsec<E, T> psc;

    @Override
    public  T parse(State<E> s)
            throws Throwable {
        Integer tran = null;
        try {
            while (true) {
                tran = s.begin();
                psc.parse(s);
                s.commit(tran);
            }
        } catch (Exception e){
            s.rollback(tran);
        }
        return null;
    }

    public Skip(Parsec<E, T> psc) {
        this.psc = psc;
    }
}
