package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.List;

public abstract class Binary extends Predicate {
    Directive _left;
    Directive _right;

    protected abstract String operator();

    @Override
    public String script() {
        return String.format("%s%s%s", _left.script(), operator(), _right.script());
    }

    @Override
    public List<Parameter<?>> parameters() {
        List<Parameter<?>> re = _left.parameters();
        re.addAll(_right.parameters());
        return re;
    }
}
