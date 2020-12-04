package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-07.
 * 跳过 0 到多次重复匹配
 */
public class Skip<E, T, Status, Tran>
    implements Parsec<E, T, Status, Tran> {
    private final Parsec<E, T, Status, Tran> psc;

    @Override
    public  T parse(State<E, Status, Tran> s)
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

    public Skip(Parsec<E, T, Status, Tran> psc) {
        this.psc = psc;
    }
}
