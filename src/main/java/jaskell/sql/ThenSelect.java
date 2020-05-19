package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.List;

public interface ThenSelect extends Directive {
    default Select select(){
        Select re = new Select();
        re._prefix = this;
        return re;
    }

    default Select select(String names) {
        Select re = new Select(names);
        re._prefix = this;
        return re;
    }

    default Select select(String... names) {
        Select re = new Select(names);
        re._prefix = this;
        return re;
    }

    default Select select(Directive... names) {
        Select re = new Select(names);
        re._prefix = this;
        return re;
    }

    class Select extends jaskell.sql.Select  {
        Directive _prefix;

        Select(){
            super();
        }

        Select(String names){
            super(names);
        }

        Select(String ... names){
            super(names);
        }

        Select(Directive... names){
            super(names);
        }

        @Override
        public String script() {
            return String.format("%s %s", _prefix.script(), super.script());
        }

        @Override
        public List<Parameter<?>> parameters() {
            List<Parameter<?>> re = _prefix.parameters();
            re.addAll(super.parameters());
            return re;
        }

        public From from(String name){
            From re = new From();
            re._from = new Name(name);
            re._select = this;
            return re;
        }

        public From from(Directive f) {
            From re = new From();
            re._select = this;
            re._from = f;
            return re;
        }
    }
}
