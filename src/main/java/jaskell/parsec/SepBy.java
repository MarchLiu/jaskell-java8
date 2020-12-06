package jaskell.parsec;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-03.
 * SepBy 尝试匹配由给定规则分隔开的0到多次重复匹配.
 */
public class SepBy<E, T, Sep, Status, Tran>
    implements Parsec<E, List<T>, Status, Tran>{
    private final Parsec<E, Sep, Status, Tran> by;
    private final Parsec<E, T, Status, Tran> p;
    @Override
    public List<T> parse(State<E, Status, Tran> s)
            throws EOFException, ParsecException {
        ArrayList<T> re = new ArrayList<>();
        try {
            re.add(this.p.parse(s));
            while (true) {
                this.by.parse(s);
                re.add(this.p.parse(s));
            }
        } catch (EOFException|ParsecException e) {
            return re;
        }
    }
    public SepBy(Parsec<E, T, Status, Tran> p, Parsec<E, Sep, Status, Tran> by) {
        this.by = new Attempt<>(by);
        this.p = new Attempt<>(p);
    }
}
