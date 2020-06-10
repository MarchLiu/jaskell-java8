package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Join implements Directive {
    Directive _prefix;
    Directive _join;

    public On on(Predicate _on){
        On re = new On();
        re._join = this;
        re._on.add(_on);
        return re;
    }

    @Override
    public String script() {
        return String.format("%s JOIN %s", _prefix.script(), _join.script());
    }

    @Override
    public List<Parameter<?>> parameters() {
        List<Parameter<?>> re = _prefix.parameters();
        re.addAll(_join.parameters());
        return re;
    }

    public static class On extends Select.From implements CouldAlias {
        Join _join;
        List<Predicate> _on = new ArrayList<>();

        public On on(Predicate _on){
            this._on.add(_on);
            return this;
        }

        public Join join(Directive other){
            Join re = new Join();
            re._prefix = this;
            re._join = other;
            return re;
        }

        @Override
        public String script() {
            return String.format("%s ON %s", _join.script(),
                    _on.stream().map(Directive::script).collect(Collectors.joining(", ")));
        }

        @Override
        public List<Parameter<?>> parameters() {
            List<Parameter<?>> re = _join.parameters();
            _on.forEach(item->re.addAll(item.parameters()));
            return re;
        }

        public Group group() {
            Group re = new Group();
            re._prefix = this;
            return re;
        }

        public Order order() {
            Order re = new Order();
            re._prefix = this;
            return re;
        }
    }
}

