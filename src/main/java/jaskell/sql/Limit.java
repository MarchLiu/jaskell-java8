package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.List;

public class Limit extends Query {
    Directive _prefix;
    Directive _limit;
    Limit(int limit){
        this._limit = new Literal(limit);
    }

    Limit(Directive limit){
        this._limit = limit;
    }

    public Offset offset(int o){
        Offset re = new Offset(o);
        re._prefix = this;
        return re;
    }

    public Offset offset(Directive o){
        Offset re = new Offset(o);
        re._prefix = this;
        return re;
    }

    @Override
    public String script() {
        return String.format("%s limit %s", _prefix.script(), _limit.script());
    }

    @Override
    public List<Parameter> parameters() {
        List<Parameter> re =  _prefix.parameters();
        re.addAll(_limit.parameters());
        return re;
    }
}
