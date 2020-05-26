package jaskell.sql;

import jaskell.script.Parameter;

import java.util.List;

public class Union extends Query implements ThenSelect {
  Query _prefix;
  Query _query;

  public Union() {
  }

  public Union(Query query) {
    this._query = query;
  }

  public All all(Query query) {
    All re = new All();
    re._prefix = this._prefix;
    re._query = query;
    return re;
  }

  @Override
  public String script() {
    if (_query == null) {
      return String.format("%s UNION", _prefix.script());
    } else {
      return String.format("%s UNION %s", _prefix.script(), _query.script());
    }
  }

  @Override
  public List<Parameter<?>> parameters() {
    List<Parameter<?>> re = _prefix.parameters();
    re.addAll(_query.parameters());
    return re;
  }

  public static class All extends Union implements ThenSelect {
    @Override
    public String script() {
      return String.format("%s UNION ALL %s", _prefix.script(), _query.script());
    }
  }
}
