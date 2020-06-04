package jaskell.parsec;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-07.
 * UDecimal 尝试将后续信息流解析成一个表示无符号浮点数的字符串,如果匹配失败就抛出异常.
 */
public class UDecimal<Status, Tran> implements Parsec<String, Character, Status, Tran> {
  private final Parsec<String, Character, Status, Tran> uint = new UInt<>();
  private final Parsec<Character, Character, Status, Tran> dot = new Ch<>('.');

  @Override
  public String parse(State<Character, Status, Tran> s)
      throws EOFException, ParsecException {
    StringBuilder builder = new StringBuilder();
    builder.append(uint.parse(s));
    if (dot.exec(s).isOk()){
      builder.append('.');
      builder.append(uint.parse(s));
    }
    return builder.toString();
  }
}
