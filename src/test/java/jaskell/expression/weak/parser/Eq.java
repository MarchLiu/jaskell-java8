package jaskell.expression.weak.parser;

import static jaskell.parsec.common.Txt.skipWhiteSpaces;
import static jaskell.parsec.common.Txt.text;

import jaskell.expression.parser.Parser;
import jaskell.expression.weak.Equals;
import jaskell.expression.Expression;
import jaskell.parsec.ParsecException;
import jaskell.parsec.common.Parsec;
import jaskell.parsec.common.SkipWhitespaces;
import jaskell.parsec.common.State;

import java.io.EOFException;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/04 17:33
 */
public class Eq implements Parsec<Expression, Character> {
  private final SkipWhitespaces skips = skipWhiteSpaces();
  private final Parsec<?, Character> op = skips.then(text("==")).then(skips);
  private final Expression prev;

  public Eq(Expression prev) {
    this.prev = prev;
  }

  @Override
  public Equals parse(State<Character> s) throws EOFException, ParsecException {
    Parsec<Expression, Character> parser = new WeakParser();
    op.parse(s);
    return new Equals(prev, parser.parse(s));
  }
}
