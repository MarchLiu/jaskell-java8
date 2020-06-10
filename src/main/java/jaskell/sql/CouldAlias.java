package jaskell.sql;

import jaskell.script.Directive;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/10 21:01
 */
public interface CouldAlias extends Directive {
  default Alias as(String name){
    Alias re = new Alias(name);
    re._query = this;
    return re;
  }
}
