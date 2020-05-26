package jaskell.sql;

import jaskell.script.Parameter;

import java.util.List;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/01 17:09
 */
public class Sum extends Literal {
  private Name _field = null;
  Sum(){

  }
  Sum(String name){
    _field = new Name(name);
  }
  Sum(Name name){
    _field = name;
  }
  @Override
  public String script() {
    if(_field != null) {
      return String.format("SUM(%s)", _field.script());
    }else{
      return "SUM(*)";
    }
  }

  @Override
  public List<Parameter<?>> parameters() {
    return _field.parameters();
  }
}

