package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.List;

public class In implements Directive {
    Directive _statement;

    In(Directive statement){
        this._statement = statement;
    }

    @Override
    public String script() {
        return String.format("in (%s)", _statement.script());
    }

    @Override
    public List<Parameter> parameters() {
        return _statement.parameters();
    }
}
