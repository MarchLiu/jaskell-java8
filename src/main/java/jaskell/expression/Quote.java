package jaskell.expression;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/04 11:25
 */
public class Quote implements Expression {
  private final Expression expression;

  public Quote(Expression expression) {
    this.expression = expression;
  }

  @Override
  public double eval() {
    return expression.eval();
  }

  @Override
  public Expression makeAst() {
    return new Quote(this.expression.makeAst());
  }

}
