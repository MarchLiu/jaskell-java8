package jaskell.parsec.common;

import static jaskell.parsec.common.Combinator.attempt;

import jaskell.parsec.ParsecException;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-03.
 * ManyTil 尝试匹配 parser 0 到多次,直到终结算子成功,它是饥饿模式.
 */
public class ManyTill<E, T, L>
    implements Parsec<E, List<T>> {
    private final Parsec<E, T> parser;
    private final Parsec<E, L> end;
    @Override
    public List<T> parse(State<E> s)
            throws Exception {
        List<T> re = new ArrayList<>();
        while (true) {
            try {
                attempt(end).parse(s);
                return re;
            } catch (EOFException | ParsecException e) {
                re.add(parser.parse(s));
            }
        }
    }

    public ManyTill(Parsec<E, T> parser, Parsec<E, L> end) {
        this.parser = new Attempt<>(parser);
        this.end = end;
    }
}
