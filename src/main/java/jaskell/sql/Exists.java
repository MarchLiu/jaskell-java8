package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exists extends Predicate {
    Optional<Directive> _prefix;
    Directive directive;

    Exists(Directive directive){
        _prefix = Optional.empty();
        this.directive = directive;
    }

    @Override
    public String script() {
        if(_prefix.isPresent()) {
            return String.format("%s EXISTS(%s)", _prefix.get().script(), directive.script());
        }else {
            return String.format("EXISTS(%s)", directive.script());
        }
    }

    @Override
    public List<Parameter<?>> parameters() {
        if (_prefix.isPresent()){
            return Stream.concat(_prefix.get().parameters().stream(), directive.parameters().stream())
                    .collect(Collectors.toList());
        }else {
            return directive.parameters();
        }
    }
}
