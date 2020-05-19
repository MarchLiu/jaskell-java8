package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.List;

public class Offset extends Query {
    Directive _prefix;
    Directive _offset;
    Offset(int offset){
        this._offset = new Literal(offset);
    }

    Offset(Directive offset){
        this._offset = offset;
    }

    @Override
    public String script() {
        return String.format("%s offset %s", _prefix.script(), _offset.script());
    }

    @Override
    public List<Parameter<?>> parameters() {
        List<Parameter<?>> re =  _prefix.parameters();
        re.addAll(_offset.parameters());
        return re;
    }
}
