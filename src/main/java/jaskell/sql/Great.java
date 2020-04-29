package jaskell.sql;

public class Great extends Binary {
    @Override
    protected String operator() {
        return " > ";
    }
}
