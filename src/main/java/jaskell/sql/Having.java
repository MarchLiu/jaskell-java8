package jaskell.sql;

import jaskell.script.Parameter;

import java.util.ArrayList;
import java.util.List;

public class Having extends Query {
    Group.By _by;
    Predicate _predicate;

    public Having(Predicate _predicate){
        this._predicate = _predicate;
    }

    @Override
    public String script() {
        return String.format("%s HAVING %s",
                _by.script(),
                _predicate.script());
    }

    @Override
    public List<Parameter<?>> parameters() {
        List<Parameter<?>> re = new ArrayList<Parameter<?>>();
        re.addAll(_by.parameters());
        re.addAll(_predicate.parameters());
        return re;
    }

    public Order order(){
        Order re = new Order();
        re._prefix = this;
        return re;
    }
}
