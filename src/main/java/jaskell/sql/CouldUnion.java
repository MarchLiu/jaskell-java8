package jaskell.sql;

import jaskell.script.Directive;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/10 20:54
 */
public interface CouldUnion extends Directive {
  default Union union(String name){
    Union result = new Union();
    result._prefix = this;
    return result;
  }
}
