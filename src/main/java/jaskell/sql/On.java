package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.List;

public class On implements Directive {
    Directive _prefix;

    @Override
    public String script() {
        return String.format("%s ON", _prefix.script());
    }

    @Override
    public List<Parameter<?>> parameters() {
        return _prefix.parameters();
    }

    public Conflict conflict(){
        Conflict re = new Conflict();
        re._prefix = this;
        return re;
    }

    public Conflict conflict(String names){
        Conflict re = new Conflict(names);
        re._prefix = this;
        return re;
    }

    public Conflict conflict(String ... names){
        Conflict re = new Conflict(names);
        re._prefix = this;
        return re;
    }
}
