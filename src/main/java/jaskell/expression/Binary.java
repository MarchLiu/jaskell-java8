package jaskell.expression;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/04 10:52
 */
abstract class Binary implements Expression {
  protected Expression left;
  protected Expression right;

  public abstract int getPriority();

  @Override
  public Expression makeAst() {
    this.left = this.left.makeAst();
    this.right = this.right.makeAst();
    if (right instanceof Binary) {
      Binary r = (Binary) right;
      if (this.getPriority() >= r.getPriority()) {
        Expression lx = r.left;
        this.right = lx;
        r.left = this;
        return r;
      }
    }
    return this;
  }
}
