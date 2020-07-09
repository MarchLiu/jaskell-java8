package jaskell.expression;

import jaskell.expression.Compare;
import jaskell.expression.Env;
import jaskell.expression.Expression;
import jaskell.expression.ExpressionException;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/04 17:31
 */
public class Equals extends Compare {
  public Equals(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }


  @Override
  public boolean compare(Expression l, Expression r, Env env) throws ExpressionException {
    return l.eval(env) == right.eval(env);
  }
}
