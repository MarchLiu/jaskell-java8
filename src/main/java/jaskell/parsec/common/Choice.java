package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-03.
 * Choice 算子是多路分支选择算子, choice 顺序检查所有分路,返回第一个成功的算子的解析结果.如果某个算子解析失败以后没有复位,则将其错误
 * 信息抛出.如果所有的分路都解析失败,抛出异常.
 */
public class Choice<T, E>
    implements Parsec<T, E> {
  private final List<Parsec<T, E>> parsecs;

  @Override
  public T parse(State<E> s) throws EOFException, ParsecException {
    Exception err = null;
    Integer status = s.status();
    for (Parsec<T, E> psc : this.parsecs) {
      try {
        return psc.parse(s);
      } catch (EOFException | ParsecException e) {
        err = e;
        if (!s.status().equals(status)) {
          throw e;
        }
      }
    }
    if (err == null) {
      throw s.trap("Choice Error : All parsec parser failed.");
    } else {
      String message = String.format("Choice Error %s, stop at %s", err, s.status());
      throw s.trap(message);
    }
  }

  @SafeVarargs
  public Choice(Parsec<T, E>... parsecs) {
    this.parsecs = Arrays.asList(parsecs);
  }

  public Choice(List<Parsec<T, E>> parsecs) {
    this.parsecs = new ArrayList<>(parsecs);
  }

}
