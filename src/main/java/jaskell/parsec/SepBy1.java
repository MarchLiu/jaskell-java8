package jaskell.parsec;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-03.
 * SepBy 尝试匹配由给定规则分隔开的1到多次重复匹配.
 */
public class SepBy1<E, T, Sep, Status, Tran>
    implements Parsec<E, List<T>, Status, Tran> {
    private final Parsec<E, Sep, Status, Tran> by;
    private final Parsec<E, T, Status, Tran> p;
    @Override
    public List<T> parse(State<E, Status, Tran> s)
            throws EOFException, ParsecException {
        List<T> re = new ArrayList<>();
        re.add(this.p.parse(s));
        Parsec<E, T, Status, Tran> parser = new Attempt<>(p);
        try {
            while (true) {
                this.by.parse(s);
                re.add(parser.parse(s));
            }
        } catch (EOFException|ParsecException e) {
            return re;
        }
    }

    public SepBy1(Parsec<E, T, Status, Tran> p, Parsec<E, Sep, Status, Tran> by) {
        this.by = new Attempt<>(by);
        this.p = p;
    }
}
