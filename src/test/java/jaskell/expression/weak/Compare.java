package jaskell.expression.weak;

import jaskell.expression.Binary;
import jaskell.expression.Env;
import jaskell.expression.Expression;
import jaskell.expression.ExpressionException;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/04 17:24
 */
public abstract class Compare extends Binary {
  @Override
  public int getPriority() {
    return 3;
  }

  @Override
  public double eval(Env env) throws ExpressionException {
    if (left instanceof Compare) {
      return toInt(((Compare) left).compare(((Compare) left).left, ((Compare) left).right, env) &&
        compare(((Compare)left).right, right, env));
    } else {
      return toInt(compare(left, right, env));
    }
  }

  public int toInt(boolean value) {
    return value ? 1: 0;
  }

  public abstract boolean compare(Expression l, Expression r, Env env) throws ExpressionException;
}
