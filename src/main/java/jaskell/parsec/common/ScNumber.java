package jaskell.parsec.common;

import jaskell.parsec.ParsecException;
import jaskell.sql.Right;
import jaskell.util.Result;

import java.io.EOFException;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/05 14:40
 */
public class ScNumber implements Parsec<String, Character> {
  private final Decimal decimal = new Decimal();
  private final Parsec<Character, Character> ep = new Ch('e', false);
  private final Parsec<String, Character> sp =
      new Choice<>(new Try<>(new Text("+")), new Try<>(new Text("-")), new Return<>(""));
  private final Parsec<String, Character> np = new UInt();

  private final Parsec<String, Character> exp = new Try<>(s -> ep.parse(s) + sp.parse(s) + np.parse(s));

  @Override
  public String parse(State<Character> s) throws EOFException, ParsecException {
    String mantissa = decimal.parse(s);
    return exp.exec(s).flatMap(e -> new Result<>(mantissa + e)).orElse(mantissa);
  }
}
