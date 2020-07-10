package jaskell.expression.weak.parser;

import static jaskell.parsec.common.Combinator.between;
import static jaskell.parsec.common.Txt.ch;
import static jaskell.parsec.common.Txt.skipWhiteSpaces;

import jaskell.expression.Expression;
import jaskell.expression.Quote;
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
 * @since 2020/06/04 11:31
 */
public class Q implements Parsec<Expression, Character> {
  @Override
  public Quote parse(State<Character> s) throws EOFException, ParsecException {
    WeakParser p = new WeakParser();
    SkipWhitespaces skips = skipWhiteSpaces();
    Parsec<Expression, Character> parser = between(ch('(').then(skips), skips.then(ch(')')), p);
    return new Quote(parser.parse(s));
  }
}
