package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.ArrayList;
import java.util.List;

public class Case implements Directive {
    Directive _argument = null;

    Case(){

    }

    Case(Directive argument){
        this._argument = argument;
    }

    @Override
    public String script() {
        if(_argument == null) {
            return String.format("CASE");
        }else{
            return String.format("CASE %s", _argument.script());
        }
    }

    @Override
    public List<Parameter<?>> parameters() {
        if(_argument != null){
            return _argument.parameters();
        }
        return new ArrayList<>();
    }

    public static class When implements Directive {
        Directive _prefix;
        Directive _cond;

        When(Directive cond){
            this._cond = cond;
        }

        @Override
        public String script() {
            return String.format("%s WHEN %s", _prefix.script(), _cond.script());
        }

        @Override
        public List<Parameter<?>> parameters() {
            List<Parameter<?>> re = _prefix.parameters();
            re.addAll(_cond.parameters());
            return re;
        }

        public Then then(Directive expr){
            Then re = new Then(expr);
            re._prefix = this;
            return re;
        }

    }

    public static class Then implements Directive {
        Directive _prefix;
        Directive _expr;

        Then(Directive expr){
            this._expr = expr;
        }

        @Override
        public String script() {
            return String.format("%s THEN %s", _prefix.script(), _expr.script());
        }

        @Override
        public List<Parameter<?>> parameters() {
            List<Parameter<?>> re = _prefix.parameters();
            re.addAll(_expr.parameters());
            return re;
        }

        public When when(Predicate predicate){
            When re = new When(predicate);
            re._prefix = this;
            return re;
        }

        public End end(){
            End re = new End();
            re._prefix = this;
            return re;
        }

        public Else _else(Directive expr){
            Else re = new Else(expr);
            re._prefix = this;
            return re;
        }
    }

    public static class Else implements Directive {
        Directive _prefix;
        Directive _expr;

        Else(Directive expr){
            this._expr = expr;
        }

        @Override
        public String script() {
            return String.format("%s ELSE %s", _prefix.script(), _expr.script());
        }

        @Override
        public List<Parameter<?>> parameters() {
            List<Parameter<?>> re = _prefix.parameters();
            re.addAll(_expr.parameters());
            return re;
        }

        public End end(){
            End re = new End();
            re._prefix = this;
            return re;
        }
    }

    public static class End implements Directive {
        Directive _prefix;

        @Override
        public String script() {
            return String.format("%s END", _prefix.script());
        }

        @Override
        public List<Parameter<?>> parameters() {
            return _prefix.parameters();
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
    }

    public static class Alias implements Directive{
        Directive _prefix;
        Directive _name;

        Alias(String alias) {
            this._name = new Name(alias);
        }

        Alias(Literal alias) {
            this._name = new Name(alias.script());
        }

        @Override
        public String script() {
            return String.format("%s AS %s", _prefix.script(), _name.script());
        }

        @Override
        public List<Parameter<?>> parameters() {
            List<Parameter<?>> re = _prefix.parameters();
            re.addAll(_name.parameters());
            return re;
        }
    }
}
