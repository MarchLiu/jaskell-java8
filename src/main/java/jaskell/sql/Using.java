package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Using implements Directive {
    Directive _prefix;
    List<Directive> _items;
    Using(String names){
        this._items = Arrays.stream(names.split(","))
                .map(String::trim).map(Name::new).collect(Collectors.toList());;
    }

    Using(String ... names){
        this._items = Arrays.stream(names).map(Name::new).collect(Collectors.toList());
    }

    Using(Directive... names){
        this._items = Arrays.asList(names);
    }

    public Where where(Predicate predicate){
        Where re = new Where(predicate);
        re._from = this;
        return re;
    }

    @Override
    public String script() {
        return String.format("%s USING(%s)",
                _prefix.script(),
                _items.stream().map(Directive::script).collect(Collectors.joining(",")));
    }

    @Override
    public List<Parameter<?>> parameters() {
        return null;
    }

    public static class Where extends Query {
        Directive _from;
        Predicate _predicate;

        Where(Predicate predicate){

        }
        @Override
        public String script() {
            return null;
        }

        @Override
        public List<Parameter<?>> parameters() {
            return null;
        }
    }
}
