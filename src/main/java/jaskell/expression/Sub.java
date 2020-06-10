package jaskell.expression;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/04 10:57
 */
public class Sub extends Binary {
  public Sub(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public double eval(Env env) throws ExpressionException {
    return left.eval(env) - right.eval(env);
  }

  @Override
  public int getPriority() {
    return 1;
  }
}
