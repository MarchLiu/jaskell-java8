package jaskell.util;

import java.util.Objects;


/**
 * @param <T> type of arg1
 * @param <U> type of arg2
 * @param <R> type of result
 */
public interface BiFunction<T, U, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
     */
    R apply(T t, U u) throws Exception;

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
    default <O> BiFunction<T, U, O> andThen(Function<? super R, ? extends O> after) {
        Objects.requireNonNull(after);
        return (T t, U u) -> after.apply(apply(t, u));
    }

    default Try<R> collect(T t, U u) {
        try {
            return Try.success(apply(t, u));
        } catch (Exception e) {
            return Try.failure(e);
        }
    }

    default Try<R> collect(Tuple2<T, U> tuple) {
        return collect(tuple.getItem0(), tuple.getItem1());
    }


    default R apply(Tuple2<T, U> tuple) throws Exception {
        return this.apply(tuple.getItem0(), tuple.getItem1());
    }

    default Function<U, R> curry(T t) {
        return u -> apply(t, u);
    }
}
