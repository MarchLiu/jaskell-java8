package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.List;

public class Where extends Query {
    Directive _prefix;
    Predicate _predicate;

    Where(Predicate predicate){
        this._predicate = predicate;
    }

    @Override
    public String script() {
        return String.format("%s WHERE %s", _prefix.script(), _predicate.script());
    }

    @Override
    public List<Parameter<?>> parameters() {
        List<Parameter<?>> re = _prefix.parameters();
        re.addAll(_predicate.parameters());
        return re;
    }

    public Group group() {
        Group re = new Group();
        re._prefix = this;
        return re;
    }

    public Order order() {
        Order re = new Order();
        re._prefix = this;
        return re;
    }
}
