package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.List;

public class Do implements Directive {
    Directive _prefix;

    @Override
    public String script() {
        return String.format("%s do", _prefix.script());
    }

    @Override
    public List<Parameter> parameters() {
        return _prefix.parameters();
    }

    public Nothing nothing(){
        Nothing re = new Nothing();
        re._prefix = this;
        return re;
    }

    public Update update(){
        Update re = new Update();
        re._prefix = this;
        return re;
    }

    public class Update implements Directive {
        Directive _prefix;

        @Override
        public String script() {
            return String.format("%s update", _prefix.script());
        }

        @Override
        public List<Parameter> parameters() {
            return _prefix.parameters();
        }

        public jaskell.sql.Update.Set set(String field, Directive value){
            jaskell.sql.Update.Set re = new jaskell.sql.Update.Set(field, value);
            re._prefix = this;
            return re;
        }

        public jaskell.sql.Update.Set set(Directive field, Directive value){
            jaskell.sql.Update.Set re = new jaskell.sql.Update.Set(field, value);
            re._prefix = this;
            return re;
        }
    }
}
