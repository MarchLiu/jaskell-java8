package jaskell.sql;

public class GreateOrEqual extends Binary{
    @Override
    protected String operator() {
        return ">=";
    }
}
