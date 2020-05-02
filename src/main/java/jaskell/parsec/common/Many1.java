package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-03.
 * Many1 匹配给定算子 1 到多次.
 */
public class Many1<T, E>
    implements Parsec<List<T>, E> {
    private final Parsec<T, E> parser;

    @Override
    public List<T> parse(State<E> s) throws EOFException, ParsecException {
        List<T> re = new ArrayList<>();
        re.add(this.parser.parse(s));
        Parsec<T, E> p = new Try<>(parser);
        try{
            while (true){
                re.add(p.parse(s));
            }
        } catch (Exception e){
            return re;
        }
    }

    public Many1(Parsec<T, E> parsec){
        this.parser = parsec;
    }
}
