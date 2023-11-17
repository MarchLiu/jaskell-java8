package jaskell.util;

import java.util.Objects;
import java.util.function.Function;


/**
 * @param <T> type of arg1
 * @param <U> type of arg2
 * @param <V> type of arg3
 * @param <W> type of arg4
 * @param <X> type of arg5
 * @param <Y> type of arg6
 * @param <Z> type of arg7
 * @param <R> type of result
 */
public interface Function7<T, U, V, W, X, Y, Z, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
     */
    R apply(T t, U u, V v, W w, X x, Y y, Z z) throws Exception;

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <O> the type of output of the {@code after} function, and of the
     *           composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     */
    default <O> Function7<T, U, V, W, X, Y, Z, O> andThen(Function<? super R, ? extends O> after) {
        Objects.requireNonNull(after);
        return (T t, U u, V v, W w, X x, Y y, Z z) -> after.apply(apply(t, u, v, w, x, y, z));
    }

    default Try<R> tryIt(T t, U u, V v, W w, X x, Y y, Z z) {
        try {
            return Try.success(apply(t, u, v, w, x, y, z));
        } catch (Exception e) {
            return Try.failure(e);
        }
    }
}
