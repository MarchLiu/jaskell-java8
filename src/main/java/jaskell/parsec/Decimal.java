package jaskell.parsec;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-08.
 * Decimal 尝试将后续的信息解析为 Decimal ,直到第一个无效信息为止.如果获取的信息不足以组成一个有效的浮点数,抛出异常.
 */
public class Decimal<Status, Tran> implements Parsec<String, Character, Status, Tran> {

  private final Parsec<Character, Character, Status, Tran> sign = new Ch<>('-');
  private final UDecimal<Status, Tran> decimal = new UDecimal<>();

  @Override

  public String parse(State<Character, Status, Tran> s)
      throws EOFException, ParsecException {
    if (sign.exec(s).isOk()) {
      return "-" + decimal.parse(s);
    } else {
      return decimal.parse(s);
    }
  }
}
