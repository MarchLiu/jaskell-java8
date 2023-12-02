package jaskell.util;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2023/11/16 18:44
 */
public interface Supplier<T> extends Triable<T> {
    T get() throws Exception;

    default Try<T> collect() {
        try {
            return Try.success(get());
        } catch (Exception e) {
            return Try.failure(e);
        }
    }
}
