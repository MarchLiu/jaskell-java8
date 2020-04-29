package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Update implements Directive {
    private Name _table;
    public Update(String name){
        this._table = new Name(name);
    }

    public Update(Name name){
        this._table = name;
    }

    public Set set(String field, Directive value){
        Set re = new Set(field, value);
        re._prefix = this;
        return re;
    }

    public Set set(Directive field, Directive value){
        Set re = new Set(field, value);
        re._prefix = this;
        return re;
    }

    @Override
    public String script() {
        return String.format("update %s", _table.script());
    }

    @Override
    public List<Parameter> parameters() {
        return _table.parameters();
    }

    public static class Set extends Query {
        Directive _prefix;
        List<Equation> _sets = new ArrayList<>();

        Set(String field, Directive value){
            _sets.add(new Equation(new Name(field), value));
        }

        public Set(Directive field, Directive value){
            this._sets.add(new Equation(field, value));
        }

        public Set set(String field, Directive value){
            _sets.add(new Equation(field, value));
            return this;
        }

        public Set set(Directive field, Directive value){
            _sets.add(new Equation(field, value));
            return this;
        }

        @Override
        public String script() {
            return String.format("%s set %s",
                    _prefix.script(),
                    _sets.stream().map(Directive::script).collect(Collectors.joining(", ")));
        }

        @Override
        public List<Parameter> parameters() {
            List<Parameter> re = _prefix.parameters();
            _sets.forEach(item -> re.addAll(item.parameters()));
            return re;
        }

        public Using using(String names){
            Using re = new Using(names);
            re._prefix = this;
            return re;
        }

        public Using using(String... names){
            Using re = new Using(names);
            re._prefix = this;
            return re;
        }

        public Using using(Directive ... names){
            Using re = new Using(names);
            re._prefix = this;
            return re;
        }

        public Where where(Predicate predicate){
            Where re = new Where(predicate);
            re._prefix = this;
            return re;
        }
    }

    public static class Equation implements Directive {
        Directive _field;
        Directive _value;

        Equation(String field, Directive value){
            this._field = new Name(field);
            this._value = value;
        }

        Equation(Directive field, Directive value){
            this._field = field;
            this._value = value;
        }

        @Override
        public String script() {
            return String.format("%s=%s", _field.script(), _value.script());
        }

        @Override
        public List<Parameter> parameters() {
            List<Parameter> re = _field.parameters();
            re.addAll(_value.parameters());
            return re;
        }
    }
}
