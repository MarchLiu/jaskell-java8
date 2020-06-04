package jaskell.expression.parser;

import static jaskell.parsec.common.Atom.ne;
import static jaskell.parsec.common.Combinator.ahead;
import static jaskell.parsec.common.Combinator.attempt;
import static jaskell.parsec.common.Combinator.choice;
import static jaskell.parsec.common.Txt.ch;
import static jaskell.parsec.common.Txt.skipWhiteSpaces;

import jaskell.expression.Expression;
import jaskell.parsec.Ahead;
import jaskell.parsec.ParsecException;
import jaskell.parsec.common.Eof;
import jaskell.parsec.common.Parsec;
import jaskell.parsec.common.SkipWhitespaces;
import jaskell.parsec.common.State;

import java.io.EOFException;

import static jaskell.parsec.common.Atom.eof;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/04 11:16
 */
public class Parser implements Parsec<Expression, Character> {
  private final Parsec<Character, Character> rq = attempt(ch(')'));
  private final SkipWhitespaces skips = skipWhiteSpaces();
  private final Eof<Character> e = eof();
  private final Parsec<?, Character> end = ahead(choice(rq, e));

  @Override
  public Expression parse(State<Character> s) throws EOFException, ParsecException {
    Parsec<Expression, Character> np = choice(attempt(new N()), attempt(new Q()));

    Expression left = np.parse(s);
    skips.parse(s);
    if (end.exec(s).isOk()) {
      return left;
    } else {
      Parsec<Expression, Character> next = choice(attempt(new A(left)),
          attempt(new S(left)),
          attempt(new P(left)),
          attempt(new D(left)));
      return next.parse(s);
    }
  }
}
