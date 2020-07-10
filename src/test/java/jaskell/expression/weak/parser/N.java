package jaskell.expression.weak.parser;

import jaskell.expression.Expression;
import jaskell.expression.Number;
import jaskell.parsec.ParsecException;
import jaskell.parsec.common.Parsec;
import jaskell.parsec.common.ScNumber;
import jaskell.parsec.common.State;

import java.io.EOFException;


/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/04 10:53
 */
public class N implements Parsec<Expression, Character> {
  private final Parsec<String, Character> number = new ScNumber();
  @Override
  public Number parse(State<Character> s) throws EOFException, ParsecException {
    String re = number.parse(s);
    return new Number(Double.parseDouble(re));
  }
}
