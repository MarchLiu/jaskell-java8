package jaskell.expression;

import java.util.Optional;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/10 18:03
 */
public class Parameter implements Expression {
  private final String name;

  public Parameter(String name) {
    this.name = name;
  }

  @Override
  public double eval(Env env) throws ExpressionException {
    Optional<Expression> value = env.get(name);
    if (value.isPresent()) {
      return value.get().eval(env);
    } else {
      throw new ExpressionException(name + " not found");
    }
  }

  @Override
  public Expression makeAst() {
    return this;
  }
}
