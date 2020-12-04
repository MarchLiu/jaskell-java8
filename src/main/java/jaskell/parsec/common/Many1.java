package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-03.
 * Many1 匹配给定算子 1 到多次.
 */
public class Many1<E, T>
    implements Parsec<E, List<T>> {
    private final Parsec<E, T> parser;

    @Override
    public List<T> parse(State<E> s) throws EOFException, ParsecException {
        List<T> re = new ArrayList<>();
        re.add(this.parser.parse(s));
        Parsec<E, T> p = new Try<>(parser);
        try{
            while (true){
                re.add(p.parse(s));
            }
        } catch (Exception e){
            return re;
        }
    }

    public Many1(Parsec<E, T> parsec){
        this.parser = parsec;
    }
}
