package jaskell.util;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2023/11/16 18:42
 */
public interface Function<T, U> {
    U apply(T arg) throws Exception;

    default Try<U> collect(T arg) {
        try {
            return Try.success(apply(arg));
        } catch (Exception err) {
            return Try.failure(err);
        }
    }

    default <R> Function<T, R> andThen(Function<? super U, ? extends R> other) {
        return (T arg)-> other.apply(apply(arg));
    }

}
