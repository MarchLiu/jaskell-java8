package jaskell.util;

import java.util.Objects;
import java.util.function.Function;


/**
 * @param <T> type of arg1
 * @param <U> type of arg2
 * @param <V> type of arg3
 * @param <R> type of result
 */
public interface TriFunction<T, U, V, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
     */
    R apply(T t, U u, V v) throws Throwable;

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
    default <O> TriFunction<T, U, V, O> andThen(Function<? super R, ? extends O> after) {
        Objects.requireNonNull(after);
        return (T t, U u, V v) -> after.apply(apply(t, u, v));
    }

    default Try<R> tryIt(T t, U u, V v) {
        try {
            return Try.success(apply(t, u, v));
        } catch (Throwable e) {
            return Try.failure(e);
        }
    }
}
