package jaskell.parsec;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-03.
 * Many1 匹配给定算子 1 到多次.
 */
public class Many1<E, T, Status, Tran>
    implements Parsec<E, List<T>, Status, Tran> {
    private final Parsec<E, T, Status, Tran> parser;

    @Override
    public List<T> parse(State<E, Status, Tran> s) throws EOFException, ParsecException {
        List<T> re = new ArrayList<>();
        re.add(this.parser.parse(s));
        Parsec<E, T, Status, Tran> p = new Attempt<>(parser);
        try{
            while (true){
                re.add(p.parse(s));
            }
        } catch (Exception e){
            return re;
        }
    }

    public Many1(Parsec<E, T, Status, Tran> parsec){
        this.parser = parsec;
    }
}
