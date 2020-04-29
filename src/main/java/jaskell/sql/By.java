package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class By extends Select.From {
    Directive _prefix;
    private List<Directive> _fields = new ArrayList<>();

    public By(String names){
        _fields.addAll(Arrays.stream(
                names.split(",")).map(String::trim).map(Name::new).collect(Collectors.toList()));
    }

    public By(String... names){
        _fields.addAll(Arrays.stream(names).map(String::trim).map(Name::new).collect(Collectors.toList()));
    }

    public By(Directive ... names){
        _fields.addAll(Arrays.asList(names));
    }

    public By by(String names){
        _fields.addAll(Arrays.stream(
                names.split(",")).map(String::trim).map(Name::new).collect(Collectors.toList()));
        return this;
    }

    public By by(String... names){
        _fields.addAll(Arrays.stream(names).map(String::trim).map(Name::new).collect(Collectors.toList()));
        return this;
    }

    public By by(Directive ... names){
        _fields.addAll(Arrays.asList(names));
        return this;
    }

    @Override
    public String script() {
        return String.format("%s by %s", _prefix.script(),
                _fields.stream().map(Directive::script).collect(Collectors.joining(", ")));
    }

    @Override
    public List<Parameter> parameters() {
        List<Parameter> re = _prefix.parameters();
        _fields.forEach(item->re.addAll(item.parameters()));
        return re;
    }
}
