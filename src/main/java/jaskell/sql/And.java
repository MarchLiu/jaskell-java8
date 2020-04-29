package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.List;

public class And extends Binary {
    @Override
    protected String operator() {
        return " and ";
    }
}
