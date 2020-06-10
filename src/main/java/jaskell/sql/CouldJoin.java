package jaskell.sql;

import jaskell.script.Directive;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/10 20:57
 */
public interface CouldJoin {
  Join join(Directive other);

  Left left();

  Right right();

  Full full();

  Inner inner();

  Cross cross();
}
