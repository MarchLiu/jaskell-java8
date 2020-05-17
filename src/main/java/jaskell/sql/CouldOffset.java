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
  public Offset offset(int o);

  public Offset offset(Directive o);
}
