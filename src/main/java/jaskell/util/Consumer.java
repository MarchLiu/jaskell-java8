package jaskell.util;

import java.util.Objects;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2023/11/17 14:01
 */
public interface Consumer<T> {
    /**
     * Performs this operation on the given arguments.
     *
     * @param t the first input argument
     */
    void accept(T t) throws Exception;

    /**
     * Returns a composed {@code BiConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation.  If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code BiConsumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default Consumer<T> andThen(Consumer<? super T> after) throws Exception {
        Objects.requireNonNull(after);

        return (x) -> {
            accept(x);
            after.accept(x);
        };
    }

}
