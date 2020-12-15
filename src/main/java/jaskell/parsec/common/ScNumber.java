package jaskell.parsec.common;

import jaskell.parsec.ParsecException;
import jaskell.util.Result;
import jaskell.util.Try;

import java.io.EOFException;

/**
 * ScNumber Parser could parse scientific number text
 *
 * @author Mars Liu
 * @version 1.0.0
 * @since 2020/06/05 12:40
 */
public class ScNumber implements Parsec<Character, String> {
  private final Decimal decimal = new Decimal();
  private final Parsec<Character, Character> ep = new Ch('e', false);
  private final Parsec<Character, String> sp =
      new Choice<>(new Attempt<>(new Text("+")), new Attempt<>(new Text("-")), new Return<>(""));
  private final Parsec<Character, String> np = new UInt();

  private final Parsec<Character, String> exp = new Attempt<>(s -> ep.parse(s) + sp.parse(s) + np.parse(s));

  @Override
  public String parse(State<Character> s) throws Throwable {
    String mantissa = decimal.parse(s);
    return exp.exec(s).flatMap(e -> new Try<>(mantissa + e)).orElse(mantissa);
  }
}
