package jaskell.expression.parser;

import static jaskell.parsec.common.Txt.ch;
import static jaskell.parsec.common.Txt.skipWhiteSpaces;

import jaskell.expression.Expression;
import jaskell.expression.Product;
import jaskell.expression.Sub;
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
 * @since 2020/06/04 10:56
 */
public class P implements Parsec<Expression, Character> {
  private final SkipWhitespaces skips = skipWhiteSpaces();
  private final Parsec<?, Character> op = skips.then(ch('*')).then(skips);
  private final Expression prev;

  public P(Expression prev) {
    this.prev = prev;
  }

  @Override
  public Product parse(State<Character> s) throws EOFException, ParsecException {
    Parsec<Expression, Character> parser = new Parser();
    op.parse(s);
    return new Product(prev, parser.parse(s));
  }
}
