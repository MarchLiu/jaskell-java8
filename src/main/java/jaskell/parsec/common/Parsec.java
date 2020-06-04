package jaskell.parsec.common;

import jaskell.parsec.ParsecException;
import jaskell.util.Result;

import java.io.EOFException;
import java.util.List;

/**
 * Common Parsec 是简化的组合子接口，支持整数索引和事务标示
 * @param <T> 结果类型
 * @param <E> 输入的元素类型
 */
@FunctionalInterface
public interface Parsec<T, E> {
  T parse(State<E> s)
      throws EOFException, ParsecException;

  default <C extends List<E>> T parse(C collection) throws EOFException {
    State<E> s = new SimpleState<>(collection);
    return this.parse(s);
  }

  default T parse(String content) throws EOFException {
      State<Character> s = new TxtState(content);
      return ((Parsec<T, Character>)this).parse(s);

  }

  default Result<T, Throwable> exec(State<E> s) {
    try {
      return new Result<>(Parsec.this.parse(s));
    } catch (Exception e) {
      return new Result<>(e);
    }
  }

  default <C> Parsec<C, E> bind(Binder<T, C, E> binder) {
    return s -> {
      T value = Parsec.this.parse(s);
      return binder.bind(value).parse(s);
    };
  }

  default <C> Parsec<C, E> then(Parsec<C, E> parsec) {
    return s -> {
      Parsec.this.parse(s);
      return parsec.parse(s);
    };
  }

  default <C> Parsec<T, E> over(Parsec<C, E> parsec) {
    return s -> {
      T value = Parsec.this.parse(s);
      parsec.parse(s);
      return value;
    };
  }
}