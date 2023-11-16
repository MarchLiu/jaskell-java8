package jaskell.util;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2023/11/16 18:42
 */
public interface Function<T, U> {
    U apply(T arg) throws Throwable;

    default Try<U> tryIt(T arg) {
        try {
            return Try.success(apply(arg));
        } catch (Throwable err) {
            return Try.failure(err);
        }
    }

    default <R> Function<T, R> andThen(Function<? super U, ? extends R> other) {
        return (T arg)-> other.apply(apply(arg));
    }

}
