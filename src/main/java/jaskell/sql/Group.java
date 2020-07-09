package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.List;

public class Group implements Directive {
  Directive _prefix;

  @Override
  public String script() {
    return String.format("%s GROUP", _prefix.script());
  }

  @Override
  public List<Parameter<?>> parameters() {
    return _prefix.parameters();
  }

  public By by() {
    By re = new By();
    re._prefix = this;
    return re;
  }

  public By by(String names) {
    By re = new By(names);
    re._prefix = this;
    return re;
  }

  public By by(String... names) {
    By re = new By(names);
    re._prefix = this;
    return re;
  }

  public By by(Directive... names) {
    By re = new By(names);
    re._prefix = this;
    return re;
  }

  public By by(List<Directive> names) {
    By re = new By(names);
    re._prefix = this;
    return re;
  }


  public Order order() {
    Order re = new Order();
    re._prefix = this;
    return re;
  }

  public static class By extends jaskell.sql.By implements CouldOrder, CouldHaving {
    public By() {
      super();
    }

    public By(String names) {
      super(names);
    }

    public By(String... names) {
      super(names);
    }

    public By(Directive... names) {
      super(names);
    }

    public By(List<Directive> names) {
      super(names);
    }

  }

}
