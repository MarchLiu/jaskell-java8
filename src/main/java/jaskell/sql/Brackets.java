package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.List;

public class Brackets extends Predicate {
    Directive _segment;

    @Override
    public String script() {
        return String.format("(%s)", _segment.script());
    }

    @Override
    public List<Parameter<?>> parameters() {
        return _segment.parameters();
    }
}
