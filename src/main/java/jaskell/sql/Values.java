package jaskell.sql;


import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Values extends Statement {
    Directive _insert;
    List<Directive> _fields = new ArrayList<>();

    public Values(String fields){
        _fields.addAll(
                Arrays.stream(fields.split(",")).map(String::trim).map(Name::new).collect(Collectors.toList()));
    }

    public Values(String ... fields){
        _fields.addAll(
                Arrays.stream(fields).map(String::trim).map(Name::new).collect(Collectors.toList()));
    }

    public Values(Directive... fields){
        _fields.addAll(Arrays.asList(fields));
    }

    @Override
    public String script() {
        return String.format("%s values(%s)",
                _insert.script(),
                _fields.stream().map(Directive::script).collect(Collectors.joining(", ")));
    }

    @Override
    public List<Parameter> parameters() {
        List<Parameter> re =  _insert.parameters();
        _fields.forEach(field->re.addAll(field.parameters()));
        return re;
    }

    public On on(){
        On re = new On();
        re._prefix = this;
        return re;
    }
}
