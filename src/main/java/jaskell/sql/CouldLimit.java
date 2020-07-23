package jaskell.sql;

import jaskell.script.Directive;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/15 17:04
 */
public interface CouldLimit extends Directive {
  default Limit limit(int l) {
    Limit result = new Limit(l);
    result._prefix = this;
    return result;
  }

  default Limit limit(Directive l) {
    Limit result = new Limit(l);
    result._prefix = this;
    return result;
  }
}
