package jaskell.sql;

import jaskell.script.Directive;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/09 18:03
 */
public interface CouldHaving extends Directive {
  default Having having(Predicate predicate) {
    Having re = new Having(predicate);
    re._by = this;
    return re;
  }
}
