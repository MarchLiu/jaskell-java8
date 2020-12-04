package jaskell.expression.parser;

import static jaskell.parsec.common.Txt.ch;
import static jaskell.parsec.common.Txt.skipWhiteSpaces;

import jaskell.expression.Expression;
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
public class S implements Parsec<Character, Expression> {
  private final SkipWhitespaces skips = skipWhiteSpaces();
  private final Parsec<Character, ?> op = skips.then(ch('-')).then(skips);
  private final Expression prev;

  public S(Expression prev) {
    this.prev = prev;
  }

  @Override
  public Sub parse(State<Character> s) throws EOFException, ParsecException {
    Parsec<Character, Expression> parser = new Parser();
    op.parse(s);
    return new Sub(prev, parser.parse(s));
  }
}
