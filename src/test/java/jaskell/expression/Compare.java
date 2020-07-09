package jaskell.expression;

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
    if (right instanceof Compare) {
      return toInt(compare(left, ((Compare) right).left, env) &&
          ((Compare) right).compare(((Compare) right).left, ((Compare) right).right, env));
    } else {
      return toInt(compare(left, right, env));
    }
  }

  public int toInt(boolean value) {
    return value ? 1: 0;
  }

  public abstract boolean compare(Expression l, Expression r, Env env) throws ExpressionException;
}
