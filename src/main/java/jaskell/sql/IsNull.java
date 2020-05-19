package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.List;

public class IsNull extends Predicate {
    Directive _prefix;
    @Override
    public String script() {
        return String.format("%s is null", _prefix.script());
    }

    @Override
    public List<Parameter<?>> parameters() {
        return _prefix.parameters();
    }
}
