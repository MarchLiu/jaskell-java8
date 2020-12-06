package jaskell.parsec;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;
import static jaskell.parsec.Combinator.attempt;

/**
 * Created by Mars Liu on 2016-01-03.
 * ManyTil 尝试匹配 parser 0 到多次,直到终结算子成功,它是饥饿模式.
 */
public class ManyTill<E, T, L, Status, Tran>
    implements Parsec<E, List<T>, Status, Tran> {
    private final Parsec<E, T, Status, Tran> parser;
    private final Parsec<E, L, Status, Tran> end;
    @Override
    public List<T> parse(State<E, Status, Tran> s)
            throws EOFException, ParsecException {
        ArrayList<T> re = new ArrayList<>();
        while (true) {
            try {
                attempt(end).parse(s);
                return re;
            } catch (EOFException | ParsecException e) {
                re.add(parser.parse(s));
            }
        }
    }

    public ManyTill(Parsec<E, T, Status, Tran> parser, Parsec<E, L, Status, Tran> end) {
        this.parser = new Attempt<>(parser);
        this.end = end;
    }
}
