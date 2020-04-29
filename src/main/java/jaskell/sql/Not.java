package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Not extends Predicate {
    Optional<Directive> _predicate;
    Not(Directive predicate){
        _predicate = Optional.of(predicate);
    }

    Not(){
        _predicate = Optional.empty();
    }

    @Override
    public String script() {
        if (_predicate.isPresent()){
            return String.format("not(%s)", _predicate.get().script());
        } else {
            return "not ";
        }
    }

    @Override
    public List<Parameter> parameters() {
        if(_predicate.isPresent()){
            return _predicate.get().parameters();
        } else {
            return new ArrayList<>();
        }
    }

    public Exists exists(Directive directive) {
        Exists re = new Exists(directive);
        re._prefix = Optional.of(this);
        return re;
    }
}
