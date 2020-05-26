package jaskell.sql;

public class Less extends Binary {
    @Override
    protected String operator() {
        return " < ";
    }
}
