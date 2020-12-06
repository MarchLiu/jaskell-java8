package jaskell.parsec;

import jaskell.util.Result;
import jaskell.util.Try;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-02.
 * Parsec defined base functions of parsec parsers.
 */
@FunctionalInterface
public interface Parsec<E, T, Status, Tran> {
  T parse(State<E, Status, Tran> s)
      throws EOFException, ParsecException;

  default Try<T> exec(State<E, Status, Tran> s) {
    try {
      return new Try<>(Parsec.this.parse(s));
    } catch (Exception e) {
      return new Try(e);
    }
  }

  default <C> Parsec<E, C, Status, Tran> bind(Binder<E, T, C, Status, Tran> binder) {
    return s -> {
      T value = Parsec.this.parse(s);
      return binder.bind(value).parse(s);
    };
  }

  default <C> Parsec<E, C, Status, Tran> then(Parsec<E, C, Status, Tran> parsec) {
    return s -> {
      Parsec.this.parse(s);
      return parsec.parse(s);
    };
  }

  default <C> Parsec<E, T, Status, Tran> over(Parsec<E, C, Status, Tran> parsec) {
    return s -> {
      T value = Parsec.this.parse(s);
      parsec.parse(s);
      return value;
    };
  }
}