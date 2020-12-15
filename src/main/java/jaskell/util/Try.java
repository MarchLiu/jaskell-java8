package jaskell.util;

import java.util.function.Function;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/12/06 13:56
 */
public class Try<T>{
    final Object slot;
    final boolean _ok;
    int idx = 0;

    public Try(T val){
        slot = val;
        _ok = true;
    }

    public Try(Throwable err){
        slot = err;
        _ok = false;
    }

    public Try<T> or(Try<T> other) {
        if(_ok){
            return this;
        } else {
            return other;
        }
    }

    public <U> Try<U> map(Function<T, U> mapper) {
        if(_ok) {
            try {
                return Try.success(mapper.apply((T)slot));
            } catch (Throwable err) {
                return Try.failure(err);
            }
        } else {
            return Try.failure((Throwable) slot);
        }
    }

    public Try<T> recover(Function<Throwable, T> func) {
        if(_ok) {
            return this;
        } else {
            try {
                return Try.success(func.apply((Throwable) slot));
            } catch (Throwable err){
                return Try.failure(err);
            }
        }
    }

    public Try<T> recoverToTry(Function<Throwable, Try<T>> func) {
        if(_ok) {
            return this;
        } else {
            return func.apply((Throwable)slot);
        }
    }

    public T get() throws Throwable {
        if(_ok){
            return (T)slot;
        } else {
            throw (Throwable)slot;
        }
    }

    public T orElse(T other) throws Throwable {
        if(_ok) {
            return (T)slot;
        } else {
            return other;
        }
    }

    public T orElseGet(Try<? extends T> other) throws Throwable {
        if(_ok) {
            return (T)slot;
        } else {
            return other.get();
        }
    }

    public boolean isOk() {
        return _ok;
    }

    public boolean isErr() {
        return !_ok;
    }

    public <U> Try<U> flatMap(Function<? super T, Try<U>> mapper) {
        if (_ok) {
            return mapper.apply((T)this.slot);
        } else {
            return Try.failure((Throwable) this.slot);
        }
    }

    public static <T> Try<T> success(T value) {
        return new Try<>(value);
    }

    public static <T> Try<T> failure(Throwable err) {
        return new Try<>(err);
    }

    public static <T> Try<T> failure(String message) {
        return new Try<>(new Exception(message));
    }

}
