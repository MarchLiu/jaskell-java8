package jaskell.expression.weak.parser;

import static jaskell.parsec.common.Txt.ch;
import static jaskell.parsec.common.Txt.skipWhiteSpaces;

import jaskell.expression.Add;
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
 * @since 2020/06/04 10:56
 */
public class A implements Parsec<Expression, Character> {
  private final SkipWhitespaces skips = skipWhiteSpaces();
  private final Parsec<?, Character> op = skips.then(ch('+')).then(skips);
  private final Expression prev;

  public A(Expression prev) {
    this.prev = prev;
  }

  @Override
  public Add parse(State<Character> s) throws EOFException, ParsecException {
    Parsec<Expression, Character> parser = new WeakParser();
    op.parse(s);
    return new Add(prev, parser.parse(s));
  }
}
