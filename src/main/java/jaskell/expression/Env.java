package jaskell.expression;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/10 18:03
 */
public class Env {
  private final Map<String, Expression> data = new HashMap<>();

  public void put(String name, Double value) {
    data.put(name, new Number(value));
  }

  public void put(String name, Expression value) {
    data.put(name, value);
  }

  public Optional<Expression> get(String name) {
    if(data.containsKey(name)){
      return Optional.of(data.get(name));
    } else {
      return Optional.empty();
    }
  }
}
