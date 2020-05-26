package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.List;

public class Count extends Literal {
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
            return String.format("COUNT(%s)", _field.script());
        }else{
            return "COUNT(*)";
        }
    }

    @Override
    public List<Parameter<?>> parameters() {
        return _field.parameters();
    }
}
