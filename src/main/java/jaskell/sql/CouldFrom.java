package jaskell.sql;

import jaskell.script.Directive;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/10 20:54
 */
public interface CouldFrom {
  From from(String name);

  From from(Directive f);
}
