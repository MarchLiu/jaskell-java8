package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-03.
 * SepBy 尝试匹配由给定规则分隔开的0到多次重复匹配.
 */
public class SepBy<E, T, Sep>
    implements Parsec<E, List<T>> {
    private final Parsec<E, Sep> by;
    private final Parsec<E, T> p;
    @Override
    public List<T> parse(State<E> s)
            throws EOFException, ParsecException {
        List<T> re = new ArrayList<>();
        try {
            re.add(this.p.parse(s));
            while (true) {
                this.by.parse(s);
                re.add(this.p.parse(s));
            }
        } catch (EOFException| ParsecException e) {
            return re;
        }
    }
    public SepBy(Parsec<E, T> p, Parsec<E, Sep> by) {
        this.by = new Try<>(by);
        this.p = new Try<>(p);
    }
}
