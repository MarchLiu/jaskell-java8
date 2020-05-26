package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.ArrayList;
import java.util.List;

public class Literal extends Predicate {
    protected Literal(){}
    String _literal;

    Literal(int value){
        _literal = Integer.valueOf(value).toString();
    }

    Literal(long value){
        _literal = Long.valueOf(value).toString();
    }

    Literal(Number value){
        _literal = value.toString();
    }
    Literal(String value){ _literal = value; }
    Literal(Object value){
        _literal = String.format("%s", value);
    }

    @Override
    public String script() {
        return _literal;
    }

    @Override
    public List<Parameter<?>> parameters() {
        return new ArrayList<>();
    }

    public Alias as(String name){
        Alias re = new Alias(name);
        re._prefix = this;
        return re;
    }

    public Alias as(Name name){
        Alias re = new Alias(name.name());
        re._prefix = this;
        return re;
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

    public static class Alias implements Directive {
        Name _name;
        Directive _prefix;

        Alias(String alias) {
            this._name = new Name(alias);
        }

        Alias(Literal alias) {
            this._name = new Name(alias.script());
        }

        public String alias(){
            return _name.script();
        }

        @Override
        public String script() {
            return String.format("%s AS \"%s\"", _prefix.script(), _name.name().replace("\"", "\\\""));
        }

        @Override
        public List<Parameter<?>> parameters() {
            List<Parameter<?>> re = _prefix.parameters();
            re.addAll(_name.parameters());
            return re;
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
}
