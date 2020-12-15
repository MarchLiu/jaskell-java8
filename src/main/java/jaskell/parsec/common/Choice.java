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
public class Choice<E, T>
    implements Parsec<E, T> {
  private final List<Parsec<E, T>> parsecs;

  @Override
  public T parse(State<E> s) throws Throwable {
    Exception err = null;
    Integer status = s.status();
    for (Parsec<E, T> psc : this.parsecs) {
      try {
        return psc.parse(s);
      } catch (EOFException | ParsecException e) {
        err = e;
        if (!s.status().equals(status)) {
          throw e;
        }
      }
    }
    if(err == null){
      throw s.trap("Choice Error : All parsec parser failed.");
    } else {
      if(err instanceof EOFException) {
        throw (EOFException) err;
      }
      String message = String.format("Choice Error %s, stop at %s", err, s.status());
      throw s.trap(message);
    }
  }

  @SafeVarargs
  public Choice(Parsec<E, T>... parsecs) {
    this.parsecs = Arrays.asList(parsecs);
  }

  public Choice(List<Parsec<E, T>> parsecs) {
    this.parsecs = new ArrayList<>(parsecs);
  }

}
