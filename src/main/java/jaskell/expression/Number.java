package jaskell.expression;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/04 10:52
 */
public class Number implements Expression {
  final private java.lang.Number value;

  public Number(java.lang.Number value) {
    this.value = value;
  }

  @Override
  public double eval() {
    return value.doubleValue();
  }

  @Override
  public Expression makeAst() {
    return this;
  }
}
