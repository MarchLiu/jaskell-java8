package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Conflict implements Directive {
    Directive _prefix;
    List<Name> _items = new ArrayList<>();

    Conflict(){

    }

    Conflict(String names){
        _items.addAll(Arrays.stream(
                names.split(",")).map(String::trim).map(Name::new).collect(Collectors.toList()));
    }

    Conflict(String... names){
        _items.addAll(Arrays.stream(names).map(Name::new).collect(Collectors.toList()));
    }

    @Override
    public String script() {
        if(_items.isEmpty()) {
            return String.format("%s conflict", _prefix.script());
        } else {
            return String.format("%s conflict (%s)",
                    _prefix.script(),
                    _items.stream().map(Name::script).collect(Collectors.joining(", ")));
        }
    }

    @Override
    public List<Parameter> parameters() {
        return _prefix.parameters();
    }

    public Do do_() {
        Do re = new Do();
        re._prefix = this;
        return re;
    }

    public On on(){
        On re = new On();
        re._prefix = this;
        return re;
    }

    public Where where(String name){
        Where re = new Where(name);
        re._prefix = this;
        return re;
    }

    public Where where(Name name){
        Where re = new Where(name);
        re._prefix = this;
        return re;
    }

    public static class On implements Directive{
        Directive _prefix;

        @Override
        public String script() {
            return String.format("%s on", _prefix.script());
        }

        @Override
        public List<Parameter> parameters() {
            return _prefix.parameters();
        }

        public Constraint constraint(String name){
            Constraint re = new Constraint(name);
            re._prefix = this;
            return re;
        }

        public Constraint constraint(Literal name){
            Constraint re = new Constraint(name);
            re._prefix = this;
            return re;
        }
    }

    public static class Constraint implements Directive {
        Directive _prefix;
        Literal _constraint;

        Constraint(String name){
            _constraint = new Literal(name);
        }

        Constraint(Literal name){
            _constraint = name;
        }

        @Override
        public String script() {
            return String.format("%s constraint %s", _prefix.script(), _constraint);
        }

        @Override
        public List<Parameter> parameters() {
            return _prefix.parameters();
        }

        public Do do_(){
            Do re = new Do();
            re._prefix = this;
            return re;
        }
    }

    public static class Where extends Statement {
        Directive _prefix;
        Name _name;
        Where(String name){
            _name = new Name(name);
        }
        Where(Name name){
            _name = name;
        }

        @Override
        public String script() {
            return String.format("%s where %s", _prefix.script(), _name.script());
        }

        @Override
        public List<Parameter> parameters() {
            return _prefix.parameters();
        }

        public Do do_() {
            Do re = new Do();
            re._prefix = this;
            return re;
        }
    }
}
