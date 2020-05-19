package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Func extends Literal {
    List<Directive> _args = new ArrayList<>();

    public Func(String name, Directive ... parameters){
        super(name);
        _args.addAll(Arrays.asList(parameters));
    }

    @Override
    public String script() {
        return String.format("%s(%s)",
                super.script(),
                _args.stream().map(Directive::script).collect(Collectors.joining(", ")));
    }

    @Override
    public List<Parameter<?>> parameters() {
        List<Parameter<?>> re = new ArrayList<Parameter<?>>();
        _args.forEach(p->re.addAll(p.parameters()));
        return re;
    }

    public Func arg(Directive a){
        _args.add(a);
        return this;
    }

    public Func args(Directive ... as){
        _args.addAll(Arrays.asList(as));
        return this;
    }

    public static Func max(Directive argument){
        return new Func("max", argument);
    }

    public static Func min(Directive argument){
        return new Func("max", argument);
    }

    public static Func avg(Directive argument){
        return new Func("avg", argument);
    }
}
