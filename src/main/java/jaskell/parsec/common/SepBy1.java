package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-03.
 * SepBy 尝试匹配由给定规则分隔开的1到多次重复匹配.
 */
public class SepBy1<E, T, Sep>
    implements Parsec<E, List<T>> {
    private final Parsec<E, Sep> by;
    private final Parsec<E, T> p;
    @Override
    public List<T> parse(State<E> s)
            throws Exception {
        List<T> re = new ArrayList<>();
        re.add(this.p.parse(s));
        Parsec<E, T> parser = new Attempt<>(p);
        try {
            while (true) {
                this.by.parse(s);
                re.add(parser.parse(s));
            }
        } catch (EOFException| ParsecException e) {
            return re;
        }
    }

    public SepBy1(Parsec<E, T> p, Parsec<E, Sep> by) {
        this.by = new Attempt<>(by);
        this.p = p;
    }
}
