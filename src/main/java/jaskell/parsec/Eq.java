package jaskell.parsec;

import java.io.EOFException;
import java.util.Objects;

/**
 * Created by Mars Liu on 2016-01-03.
 * Eq 即 equal , 判断得到的信息项是否与预期相等.
 */
public class Eq<E, Status, Tran> implements Parsec<E, E, Status, Tran> {
    private final E item;
    @Override
    public  E parse(State<E, Status, Tran> s) throws EOFException, ParsecException {
        E re = s.next();
        if (Objects.equals(re, item)){
            return re;
        } else {
            String message = String.format("Expect %s is equal to %s", re, item);
            throw s.trap(message);
        }
    }

    public Eq(E item){
        this.item = item;
    }
}
