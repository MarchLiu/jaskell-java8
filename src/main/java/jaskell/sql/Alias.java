package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.List;

public class Alias implements Directive, CouldJoin {
    Directive _query;
    private final Name _name;

    public Alias(String name){
        this._name = new Name(name);
    }

    @Override
    public String script() {
        return String.format("(%s) AS %s",
                _query.script(),
                _name.script());
    }

    @Override
    public List<Parameter<?>> parameters() {
        return _query.parameters();
    }

    public Join join(Directive other){
        Join re = new Join();
        re._prefix = this;
        re._join = other;
        return re;
    }

    public Left left(){
        Left re = new Left();
        re._prefix = this;
        return re;
    }

    public Right right(){
        Right re = new Right();
        re._prefix = this;
        return re;
    }

    public Full full(){
        Full re = new Full();
        re._prefix = this;
        return re;
    }

    public Inner inner(){
        Inner re = new Inner();
        re._prefix = this;
        return re;
    }

    public Cross cross(){
        Cross re = new Cross();
        re._prefix = this;
        return re;
    }
}
