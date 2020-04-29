package jaskell.sql;

import jaskell.script.Directive;

public class Or extends Binary {
    @Override
    protected String operator() {
        return " or ";
    }
}
