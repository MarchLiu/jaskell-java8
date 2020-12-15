package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-08.
 * Decimal 尝试将后续的信息解析为 Decimal ,直到第一个无效信息为止.如果获取的信息不足以组成一个有效的浮点数,抛出异常.
 */
public class Decimal implements Parsec<Character, String> {
  private final Parsec<Character, Character> sign = new Attempt<>(new Ch('-'));
  private final Parsec<Character, String> decimal = new UDecimal();

  @Override
  public String parse(State<Character> s)
          throws Throwable {
    if (sign.exec(s).isOk()){
      return "-" + decimal.parse(s);
    } else {
      return decimal.parse(s);
    }
  }
}
