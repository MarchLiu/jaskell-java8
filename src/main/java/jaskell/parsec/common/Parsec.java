package jaskell.parsec.common;

import jaskell.parsec.ParsecException;
import jaskell.util.Result;
import jaskell.util.Try;

import java.io.EOFException;
import java.util.List;

/**
 * Common Parsec 是简化的组合子接口，支持整数索引和事务标示
 * @param <T> 结果类型
 * @param <E> 输入的元素类型
 */
@FunctionalInterface
public interface Parsec<E, T> {
  T parse(State<E> s)
      throws EOFException, ParsecException;

  default <C extends List<E>> T parse(C collection) throws EOFException {
    State<E> s = new SimpleState<>(collection);
    return this.parse(s);
  }

  default T parse(String content) throws EOFException {
      State<Character> s = new TxtState(content);
      return ((Parsec<Character, T>)this).parse(s);
  }

  default Try<T> exec(State<E> s) {
    try {
      return new Try<>(Parsec.this.parse(s));
    } catch (Exception e) {
      return new Try<>(e);
    }
  }

  default <C extends List<E>> Result<Throwable, T> exec(C collection) {
    State<E> s = new SimpleState<>(collection);
    return this.exec(s);
  }

  default Result<Throwable, T> exec(String content) {
    State<Character> s = new TxtState(content);
    return ((Parsec<Character, T>)this).exec(s);
  }

  default <C> Parsec<E, C> bind(Binder<E, T, C> binder) {
    return s -> {
      T value = Parsec.this.parse(s);
      return binder.bind(value).parse(s);
    };
  }

  default <C> Parsec<E, C> then(Parsec<E, C> parsec) {
    return s -> {
      Parsec.this.parse(s);
      return parsec.parse(s);
    };
  }

  default <C> Parsec<E, T> over(Parsec<E, C> parsec) {
    return s -> {
      T value = Parsec.this.parse(s);
      parsec.parse(s);
      return value;
    };
  }
}