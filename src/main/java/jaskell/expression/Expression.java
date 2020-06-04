package jaskell.expression;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/04 10:51
 */
public interface Expression {
  double eval();
  Expression makeAst();
}
