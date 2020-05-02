package jaskell.parsec.common;

/**
 * Common State 是简化的 State 规范，支持整型索引和事务标示
 */
public interface State<E> extends jaskell.parsec.State<E, Integer, Integer> {
}
