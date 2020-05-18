package jaskell.sql;

import jaskell.script.Directive;

import java.util.ArrayList;
import java.util.List;

public class Name extends Literal {

    protected Name(){}

    public Name(String name) {
        _literal = name;
    }

    public String name(){
        return _literal;
    }

    protected void name(String n){
        _literal = n;
    }

    public String escaped() {
        return _literal.replace("\"", "\\\"");
    }

    public String escaped(Character c) {
        return _literal.replace(c.toString(), "\\"+c.toString());
    }

    public String quotedName() {
        return String.format("\"%s\"", _literal.replace("\"", "\\\""));
    }

}
