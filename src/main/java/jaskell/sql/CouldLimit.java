package jaskell.sql;

import jaskell.script.Directive;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/15 17:04
 */
public interface CouldLimit {
  Limit limit(int l);

  Limit limit(Directive l);
}
