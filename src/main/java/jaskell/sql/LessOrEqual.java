package jaskell.sql;

public class LessOrEqual extends Binary {
    @Override
    protected String operator() {
        return " <= ";
    }
}
