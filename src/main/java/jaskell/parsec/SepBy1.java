package jaskell.parsec;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-03.
 * SepBy 尝试匹配由给定规则分隔开的1到多次重复匹配.
 */
public class SepBy1<T, Sep, E, Status, Tran>
    implements Parsec<List<T>, E, Status, Tran> {
    private final Parsec<Sep, E, Status, Tran> by;
    private final Parsec<T, E, Status, Tran> p;
    @Override
    public List<T> parse(State<E, Status, Tran> s)
            throws EOFException, ParsecException {
        List<T> re = new ArrayList<>();
        re.add(this.p.parse(s));
        Parsec<T, E, Status, Tran> parser = new Try<>(p);
        try {
            while (true) {
                this.by.parse(s);
                re.add(parser.parse(s));
            }
        } catch (EOFException|ParsecException e) {
            return re;
        }
    }

    public SepBy1(Parsec<T, E, Status, Tran> p, Parsec<Sep, E, Status, Tran> by) {
        this.by = new Try<>(by);
        this.p = p;
    }
}
