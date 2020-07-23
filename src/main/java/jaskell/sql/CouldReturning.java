package jaskell.sql;

import jaskell.script.Directive;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/15 22:18
 */
public interface CouldReturning extends Directive {
    default Returning returning() {
        Returning result = new Returning();
        result._prefix = this;
        return result;
    }

    default Returning returning(String fields){
        Returning result = new Returning(fields);
        result._prefix = this;
        return result;
    }

    default Returning returning(String ... fields){
        Returning result = new Returning(fields);
        result._prefix = this;
        return result;
    }

    default Returning returning(Directive... fields){
        Returning result = new Returning(fields);
        result._prefix = this;
        return result;
    }
}
