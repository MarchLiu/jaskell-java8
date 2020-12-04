package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-03.
 * Many 算子匹配给定算子0到多次.
 */
public class Many<E, T>
    implements Parsec<E, List<T>> {
    private final Parsec<E, T> parsec;

    @Override
    public  List<T> parse(State<E> s) throws EOFException, ParsecException {
        List<T> re = new ArrayList<>();
        try{
            while (true){
                re.add(this.parsec.parse(s));
            }
        } catch (Exception e){
            return re;
        }
    }

    public Many(Parsec<E, T> parsec) {
        this.parsec = new Try<>(parsec);
    }
}
