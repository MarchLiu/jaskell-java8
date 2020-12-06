package jaskell.util;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/12/06 13:56
 */
public class Try<T> extends Result<Throwable, T>{
    public Try(Throwable err){
        super(err);
    }

    public Try(T value) {
        super(value);
    }
}
