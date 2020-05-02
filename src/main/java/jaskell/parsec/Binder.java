package jaskell.parsec;

import java.io.EOFException;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-02.
 * Binder 为模拟 Monad 的 bind 行为提供接口抽象.
 */
public interface Binder<T, C, E, Status, Tran> {
    Parsec<C, E, Status, Tran> bind(T value) throws EOFException;
}
