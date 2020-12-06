package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-03.
 * Try 尝试执行给定算子,如果失败,先将state复位,再抛出异常.
 */
public class Attempt<E, T, Status, Tran>
    implements Parsec<E, T, Status, Tran> {
    private final Parsec<E, T, Status, Tran> parsec;

    @Override
    public T parse(State<E, Status, Tran> s) throws EOFException, ParsecException {
        Tran tran = s.begin();
        try{
            T re = this.parsec.parse(s);
            s.commit(tran);
            return re;
        } catch (Exception e) {
            s.rollback(tran);
            throw e;
        }
    }

    public Attempt(Parsec<E, T, Status, Tran> parsec){
        this.parsec = parsec;
    }
}
