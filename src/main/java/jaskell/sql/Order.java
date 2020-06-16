package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.List;

public class Order implements Directive {
  Directive _prefix;

  @Override
  public String script() {
    return String.format("%s ORDER", _prefix.script());
  }

  @Override
  public List<Parameter<?>> parameters() {
    return _prefix.parameters();
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


  public static class By extends jaskell.sql.By {
    By(String names) {
      super(names);
    }

    By(String... names) {
      super(names);
    }

    By(Directive... names) {
      super(names);
    }

    By(List<Directive> names) {
      super(names);
    }
  }
}

