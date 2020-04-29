package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-07.
 * 跳过 0 到多次重复匹配
 */
public class Skip<T, E> implements Parsec<T, E> {
    private Parsec<T, E> psc;

    @Override
    public <Status, Tran, S extends State<E, Status, Tran>>  T parse(S s)
            throws EOFException, ParsecException {
        Tran tran = null;
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

    public Skip(Parsec<T, E> psc) {
        this.psc = psc;
    }
}
