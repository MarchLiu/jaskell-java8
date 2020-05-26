package jaskell.sql;

public class Like extends Binary {
    @Override
    protected String operator() {
        return " LIKE ";
    }
}
