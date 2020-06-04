package jaskell.expression.parser;

import jaskell.expression.Expression;
import jaskell.expression.Number;
import jaskell.parsec.ParsecException;
import jaskell.parsec.common.Parsec;
import jaskell.parsec.common.State;
import static jaskell.parsec.common.Txt.decimal;

import java.io.EOFException;


/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/04 10:53
 */
public class N implements Parsec<Expression, Character> {
  private final Parsec<String, Character> decimal = decimal();
  @Override
  public Number parse(State<Character> s) throws EOFException, ParsecException {
    String re = decimal.parse(s);
    return new Number(Double.parseDouble(re));
  }
}
