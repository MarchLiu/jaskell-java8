package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Coalesce implements Directive {
    List<Directive> _args = new ArrayList<>();

    Coalesce(){
    }

    Coalesce(String names){
        _args.addAll(Arrays.stream(names.split(","))
                .map(String::trim)
                .map(Name::new)
                .collect(Collectors.toList()));
    }

    Coalesce(String... names){
        _args.addAll(Arrays.stream(names).map(Name::new).collect(Collectors.toList()));
    }

    Coalesce(Directive... directives){
        _args.addAll(Arrays.asList(directives));
    }

    public Coalesce coalesce(String names){
        _args.addAll(Arrays.stream(names.split(","))
                .map(String::trim)
                .map(Name::new)
                .collect(Collectors.toList()));
        return this;
    }

    public Coalesce coalesce(String ... names){
        _args.addAll(Arrays.stream(names).map(Name::new).collect(Collectors.toList()));
        return this;
    }

    public Coalesce coalesce(Directive ... directives){
        _args.addAll(Arrays.asList(directives));
        return this;
    }

    @Override
    public String script() {
        return String.format("coalesce(%s)",
                _args.stream().map(Directive::script).collect(Collectors.joining(", ")));
    }

    @Override
    public List<Parameter> parameters() {
        List<Parameter> re = new ArrayList<Parameter>();
        _args.forEach(p->re.addAll(p.parameters()));
        return re;
    }

    public Coalesce arg(Directive a){
        _args.add(a);
        return this;
    }

    public Coalesce args(Directive ... as){
        _args.addAll(Arrays.asList(as));
        return this;
    }
}
