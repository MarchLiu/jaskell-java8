package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.List;

public class Count implements Directive {
    private Name _field = null;
    Count(){

    }
    Count(String name){
        _field = new Name(name);
    }
    Count(Name name){
        _field = name;
    }
    @Override
    public String script() {
        if(_field != null) {
            return String.format("count(%s)", _field.script());
        }else{
            return "count(*)";
        }
    }

    @Override
    public List<Parameter> parameters() {
        return _field.parameters();
    }
}
