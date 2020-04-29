package jaskell.sql;

public class NotEqual extends Binary{
    @Override
    protected String operator() {
        return " != ";
    }
}
