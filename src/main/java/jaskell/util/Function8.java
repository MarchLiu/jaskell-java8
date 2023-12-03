package jaskell.util;

import java.util.Objects;
import java.util.function.Function;


/**
 * @param <S> type of arg1
 * @param <T> type of arg2
 * @param <U> type of arg3
 * @param <V> type of arg4
 * @param <W> type of arg5
 * @param <X> type of arg6
 * @param <Y> type of arg7
 * @param <Z> type of arg8
 * @param <R> type of result
 */
public interface Function8<S, T, U, V, W, X, Y, Z, R> {

    /**
     * Applies this function to the given arguments.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
     */
    R apply(S s, T t, U u, V v, W w, X x, Y y, Z z) throws Exception;

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
    default <O> Function8<S, T, U, V, W, X, Y, Z, O> andThen(Function<? super R, ? extends O> after) {
        Objects.requireNonNull(after);
        return (S s, T t, U u, V v, W w, X x, Y y, Z z) -> after.apply(apply(s, t, u, v, w, x, y, z));
    }

    default Try<R> collect(S s, T t, U u, V v, W w, X x, Y y, Z z) {
        try {
            return Try.success(apply(s, t, u, v, w, x, y, z));
        } catch (Exception err){
            return Try.failure(err);
        }
    }

    default R apply(Tuple8<S, T, U, V, W, X, Y, Z> tuple) throws Exception {
        return apply(tuple.getItem0(), tuple.getItem1(), tuple.getItem2(),
                tuple.getItem3(), tuple.getItem4(), tuple.getItem5(),
                tuple.getItem6(), tuple.getItem7());
    }

    default Try<R> collect(Tuple8<S, T, U, V, W, X, Y, Z> tuple) {
        return collect(tuple.getItem0(), tuple.getItem1(), tuple.getItem2(),
                tuple.getItem3(), tuple.getItem4(), tuple.getItem5(),
                tuple.getItem6(), tuple.getItem7());
    }

    default Function7<T, U, V, W, X, Y, Z, R> curry(S s) {
        return (t, u, v, w, x, y, z) -> apply(s, t, u, v, w, x, y, z);
    }

    default Function6<U, V, W, X, Y, Z, R> curry(S s, T t) {
        return (u, v, w, x, y, z) -> apply(s, t, u, v, w, x, y, z);
    }

    default Function5<V, W, X, Y, Z, R> curry(S s, T t, U u) {
        return (v, w, x, y, z) -> apply(s, t, u, v, w, x, y, z);
    }

    default Function4<W, X, Y, Z, R> curry(S s, T t, U u, V v) {
        return (w, x, y, z) -> apply(s, t, u, v, w, x, y, z);
    }

    default TriFunction<X, Y, Z, R> curry(S s, T t, U u, V v, W w) {
        return (x, y, z) -> apply(s, t, u, v, w, x, y, z);
    }

    default BiFunction<Y, Z, R> curry(S s, T t, U u, V v, W w, X x) {
        return (y, z) -> apply(s, t, u, v, w, x, y, z);
    }

    default jaskell.util.Function<Z, R> curry(S s, T t, U u, V v, W w, X x, Y y) {
        return z -> apply(s, t, u, v, w, x, y, z);
    }
}
