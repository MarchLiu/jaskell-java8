package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.ArrayList;
import java.util.List;

public class Delete implements Directive {
    @Override
    public String script() {
        return "DELETE";
    }

    @Override
    public List<Parameter<?>> parameters() {
        return new ArrayList<>();
    }

    public From from(String name) {
        return new From(name);
    }

    public From from(Directive name) {
        return new From(name);
    }

    public static class From extends Statement {
        Directive _from;

        public From(String name){
            _from = new Name(name);
        }

        public From(Directive name){
            _from = name;
        }

        @Override
        public String script() {
            return String.format("DELETE FROM %s", _from.script());
        }

        @Override
        public List<Parameter<?>> parameters() {
            return _from.parameters();
        }

        public Using using(String names) {
            Using re  = new Using(names);
            re._prefix = this;
            return re;
        }

        public Using using(String... names) {
            Using re  = new Using(names);
            re._prefix = this;
            return re;
        }

        public Using using(Directive... names) {
            Using re  = new Using(names);
            re._prefix = this;
            return re;
        }

        public Where where(Predicate predicate){
            Where re = new Where();
            re._prefix = this;
            re._predicate = predicate;
            return re;
        }

    }

    public static class Where extends Statement {
        Directive _prefix;
        Predicate _predicate;

        @Override
        public String script() {
            return String.format("%s where %s", _prefix.script(), _predicate.script());
        }

        @Override
        public List<Parameter<?>> parameters() {
            List<Parameter<?>> re = _prefix.parameters();
            re.addAll(_predicate.parameters());
            return re;
        }

        public Returning returning(String names){
            Returning re = new Returning(names);
            re._prefix = this;
            return re;
        }

        public Returning returning(String... names){
            Returning re = new Returning(names);
            re._prefix = this;
            return re;
        }

        public Returning returning(Directive... names){
            Returning re = new Returning(names);
            re._prefix = this;
            return re;
        }
    }
}
