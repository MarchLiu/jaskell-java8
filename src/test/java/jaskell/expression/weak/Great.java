package jaskell.expression.weak;

import jaskell.expression.Env;
import jaskell.expression.Expression;
import jaskell.expression.ExpressionException;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/10 16:45
 */
public class Great extends Compare {
  public Great(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }


  @Override
  public boolean compare(Expression l, Expression r, Env env) throws ExpressionException {
    return l.eval(env) > right.eval(env);
  }
}