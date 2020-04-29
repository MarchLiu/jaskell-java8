package jaskell.parsec;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-03.
 * SepBy 尝试匹配由给定规则分隔开的0到多次重复匹配.
 */
public class SepBy<T, S, E> implements Parsec<List<T>, E>{
    private Parsec<S, E> by;
    private Parsec<T, E> p;
    @Override
    public <Status, Tran, S extends State<E, Status, Tran>>  List<T> parse(S s)
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
    public SepBy(Parsec<T, E> p, Parsec<S, E> by) {
        this.by = new Try<>(by);
        this.p = new Try<>(p);
    }
}
