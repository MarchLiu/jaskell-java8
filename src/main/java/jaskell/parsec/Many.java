package jaskell.parsec;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-03.
 * Many 算子匹配给定算子0到多次.
 */
public class Many<T, E> implements Parsec<List<T>, E> {
    private Parsec<T, E> parsec;

    @Override
    public <Status, Tran, S extends State<E, Status, Tran>> List<T> parse(S s) throws EOFException, ParsecException {
        List<T> re = new ArrayList<>();
        try{
            while (true){
                re.add(this.parsec.parse(s));
            }
        } catch (Exception e){
            return re;
        }
    }

    public <P extends Parsec<T, E>> Many(P parsec) {
        this.parsec = new Try<>(parsec);
    }
}
