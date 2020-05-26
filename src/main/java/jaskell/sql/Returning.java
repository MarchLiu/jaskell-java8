package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Returning extends Query {
    Directive _prefix;
    List<Directive> _names = new ArrayList<>();

    public Returning(){

    }

    public Returning(String fields){
        _names.addAll(
                Arrays.stream(fields.split(",")).map(String::trim).map(Name::new).collect(Collectors.toList()));
    }

    public Returning(String ... fields){
        _names.addAll(
                Arrays.stream(fields).map(String::trim).map(Name::new).collect(Collectors.toList()));
    }

    public Returning(Directive... fields){
        _names.addAll(Arrays.asList(fields));
    }

    public Returning all(){
        _names.add(new Name("*"));
        return this;
    }

    public Returning returning(String fields){
        _names.addAll(
                Arrays.stream(fields.split(",")).map(String::trim).map(Name::new).collect(Collectors.toList()));
        return this;
    }

    public Returning returning(String ... fields){
        _names.addAll(
                Arrays.stream(fields).map(String::trim).map(Name::new).collect(Collectors.toList()));
        return this;
    }

    public Returning returning(Directive... fields){
        _names.addAll(Arrays.asList(fields));
        return this;
    }

    @Override
    public String script() {
        return String.format("%s RETURNING %s",
                _prefix.script(),
                _names.stream().map(Directive::script).collect(Collectors.joining(", ")));
    }

    @Override
    public List<Parameter<?>> parameters() {
        List<Parameter<?>> re = _prefix.parameters();
        _names.forEach(field->re.addAll(field.parameters()));
        return re;
    }
}
