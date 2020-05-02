package jaskell.parsec;

import java.io.EOFException;
import java.util.Set;
import static java.util.stream.Collectors.joining;

/**
 * Created by Mars Liu on 2016-01-03.
 * OneOf 即 one of ,给定项中任何一个匹配成功即视为成功,否则抛出错误.
 */
public class OneOf<E, Status, Tran> implements Parsec<E, E, Status, Tran> {
    private final Set<E> items;

    @Override
    public E parse(State<E, Status, Tran> s)
            throws EOFException, ParsecException {
        E data = s.next();
        if(items.contains(data)) {
            return data;
        }

        String message = String.format("Expect %s in [%s]", data,
                this.items.stream().map(E::toString).collect(joining(", ")));
        throw s.trap(message);
    }

    public OneOf(Set<E> items){
        this.items = items;
    }
}
