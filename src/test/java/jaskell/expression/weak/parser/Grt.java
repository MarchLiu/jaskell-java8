package jaskell.expression.weak.parser;

import static jaskell.parsec.common.Txt.skipWhiteSpaces;
import static jaskell.parsec.common.Txt.text;

import jaskell.expression.Expression;
import jaskell.expression.parser.Parser;
import jaskell.expression.weak.Great;
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
public class Grt implements Parsec<Character, Expression> {
  private final SkipWhitespaces skips = skipWhiteSpaces();
  private final Parsec<Character, ?> op = skips.then(text(">")).then(skips);
  private final Expression prev;

  public Grt(Expression prev) {
    this.prev = prev;
  }

  @Override
  public Great parse(State<Character> s) throws Throwable {
    Parsec<Character, Expression> parser = new WeakParser();
    op.parse(s);
    return new Great(prev, parser.parse(s));
  }
}
