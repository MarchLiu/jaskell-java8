package jaskell.sql;

import jaskell.script.Directive;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/15 17:04
 */
public interface CouldOffset extends Directive {
  default Offset offset(int o) {
    Offset result = new Offset(o);
    result._prefix = this;
    return result;
  }

  default Offset offset(Directive o) {
    Offset result = new Offset(o);
    result._prefix = this;
    return result;
  }
}
