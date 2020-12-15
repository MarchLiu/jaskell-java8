package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-03.
 * Try 尝试执行给定算子,如果失败,先将state复位,再抛出异常.
 */
public class Attempt<E, T>
    implements Parsec<E, T> {
    private final Parsec<E, T> parsec;

    @Override
    public T parse(State<E> s) throws Throwable {
        Integer tran = s.begin();
        try{
            T re = this.parsec.parse(s);
            s.commit(tran);
            return re;
        } catch (Throwable e) {
            s.rollback(tran);
            throw e;
        }
    }

    public Attempt(Parsec<E, T> parsec){
        this.parsec = parsec;
    }
}
