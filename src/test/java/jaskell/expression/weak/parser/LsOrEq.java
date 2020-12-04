package jaskell.expression.weak.parser;

import static jaskell.parsec.common.Txt.skipWhiteSpaces;
import static jaskell.parsec.common.Txt.text;

import jaskell.expression.Expression;
import jaskell.expression.parser.Parser;
import jaskell.expression.weak.LessOrEquals;
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
 * @since 2020/07/10 16:27
 */
public class LsOrEq  implements Parsec<Character, Expression> {
  private final SkipWhitespaces skips = skipWhiteSpaces();
  private final Parsec<Character, ?> op = skips.then(text("<=")).then(skips);
  private final Expression prev;

  public LsOrEq(Expression prev) {
    this.prev = prev;
  }

  @Override
  public LessOrEquals parse(State<Character> s) throws EOFException, ParsecException {
    Parsec<Character, Expression> parser = new WeakParser();
    op.parse(s);
    return new LessOrEquals(prev, parser.parse(s));
  }
}
