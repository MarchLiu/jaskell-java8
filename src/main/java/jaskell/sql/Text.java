package jaskell.sql;

public class Text extends Literal {
    Text(String content){
        _literal = String.format("'%s'", content.replaceAll("'", "''"));
    }
}
