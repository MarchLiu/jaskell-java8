package jaskell.sql;

import jaskell.script.Parameter;

public class JDBCParameter<T> extends Parameter<T> {
  // TODO: constructors type safe;
  @SuppressWarnings("unchecked")
  public JDBCParameter(Object key) {
    super("?", key, (Class<T>) Object.class);
  }

  public JDBCParameter(Object key, Class<T> cls) {
    super("?", key, cls);
  }

  public JDBCParameter(String placeHolder, Object key) {
    super(placeHolder, key, (Class<T>) Object.class);
  }

  public JDBCParameter(String placeHolder, Object key, Class<T> cls) {
    super(placeHolder, key, cls);
  }
  // TODO: value setters type safe;
}
