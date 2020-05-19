package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Into implements Directive, ThenSelect {
    Directive _prefix;
    Name _name;
    List<Directive> _fields = new ArrayList<>();

    Into(String table){
        _name = new Name(table);
    }

    Into(String table, String fields){
        _name = new Name(table);
        _fields.addAll(
                Arrays.stream(fields.split(",")).map(String::trim).map(Name::new).collect(Collectors.toList()));
    }

    Into(String table, String ... fields){
        _name = new Name(table);
        _fields.addAll(
                Arrays.stream(fields).map(String::trim).map(Name::new).collect(Collectors.toList()));
    }

    Into(String table, Directive ... fields){
        _name = new Name(table);
        _fields.addAll(Arrays.asList(fields));
    }

    public Into field(String field){
        _fields.add(new Name(field));
        return this;
    }

    public Into fields(String fields){
        _fields.addAll(
                Arrays.stream(fields.split(",")).map(String::trim).map(Name::new).collect(Collectors.toList()));
        return this;
    }

    public Into fields(String ... fields){
        _fields.addAll(
                Arrays.stream(fields).map(String::trim).map(Name::new).collect(Collectors.toList()));
        return this;
    }

    public Into fields(String table, Directive ... fields){
        _fields.addAll(Arrays.asList(fields));
        return this;
    }


    @Override
    public String script() {
        return String.format("%s into %s(%s)",
                _prefix.script(),
                _name.script(),
                _fields.stream().map(Directive::script).collect(Collectors.joining(", ")));
    }

    @Override
    public List<Parameter<?>> parameters() {
        List<Parameter<?>> re = new ArrayList<>(_prefix.parameters());
        _fields.forEach(item->re.addAll(item.parameters()));
        return re;
    }

    public Values values(String vs) {
        Values re = new Values(vs);
        re._insert = this;
        return re;
    }

    public Values values(String ... vs) {
        Values re = new Values(vs);
        re._insert = this;
        return re;
    }

    public Values values(Directive ... directives) {
        Values re = new Values(directives);
        re._insert = this;
        return re;

    }
}
