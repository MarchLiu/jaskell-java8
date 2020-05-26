package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.ArrayList;
import java.util.List;

public class Insert implements Directive {
    @Override
    public String script() {
        return "INSERT";
    }

    @Override
    public List<Parameter<?>> parameters() {
        return new ArrayList<>();
    }

    public Into into(String name) {
        Into re =  new Into(name);
        re._prefix = this;
        return re;
    }

    public Into into(String name, String fields) {
        Into re = new Into(name, fields);
        re._prefix = this;
        return re;
    }

    public Into into(String name, String ... fields) {
        Into re = new Into(name, fields);
        re._prefix = this;
        return re;
    }

    public Into into(String name, Directive ... fields) {
        Into re = new Into(name, fields);
        re._prefix = this;
        return re;
    }
}
