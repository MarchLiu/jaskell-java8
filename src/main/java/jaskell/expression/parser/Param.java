package jaskell.expression.parser;

import static jaskell.parsec.common.Combinator.attempt;
import static jaskell.parsec.common.Combinator.choice;
import static jaskell.parsec.common.Combinator.many;
import static jaskell.parsec.common.Txt.digit;
import static jaskell.parsec.common.Txt.joining;

import jaskell.expression.Expression;
import jaskell.expression.Parameter;
import jaskell.parsec.ParsecException;
import jaskell.parsec.common.Parsec;
import jaskell.parsec.common.State;

import java.io.EOFException;
import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/10 17:54
 */
public class Param implements Parsec<Expression, Character> {
  private final Parsec<Character, Character> head = s -> {
    Character re = s.next();
    if(Character.isLetter(re)){
      return re;
    }else {
      throw s.trap("expect a letter but get " + re.toString());
    }
  };

  private final Parsec<String, Character> tail = many(choice(attempt(head), digit())).bind(joining());

  private final Parsec<String, Character> parser = s -> head.parse(s) + tail.parse(s);

  @Override
  public Expression parse(State<Character> s) throws EOFException, ParsecException {
    return new Parameter(parser.parse(s));
  }
}
