package jaskell.parsec.common;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-02.
 * Binder 为模拟 Monad 的 bind 行为提供接口抽象.
 */
public interface Binder<E, T, C> {
    Parsec<E, C> bind(T value) throws EOFException;
}
