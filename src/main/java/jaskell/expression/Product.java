package jaskell.expression;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/04 10:57
 */
public class Product extends Binary {
  public Product(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public double eval() {
    return left.eval() * right.eval();
  }

  @Override
  public int getPriority() {
    return 2;
  }
}
