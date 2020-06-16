package jaskell.sql;

import jaskell.parsec.Eq;
import jaskell.script.Directive;
import jaskell.script.Parameter;
import org.w3c.dom.ranges.DocumentRange;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Predicate implements Directive {
    public Predicate and(Predicate predicate){
        And re = new And();
        re._left = this;
        re._right = predicate;
        return re;
    }

    public Predicate or(Predicate predicate){
        Or re = new Or();
        re._left = this;
        re._right = predicate;
        return re;
    }

    public Predicate eq(Directive predicate){
        Equal re = new Equal();
        re._left = this;
        re._right = predicate;
        return re;
    }

    public Predicate ne(Directive predicate){
        NotEqual re = new NotEqual();
        re._left = this;
        re._right = predicate;
        return re;
    }

    public Predicate gt(Directive predicate){
        Great re = new Great();
        re._left = this;
        re._right = predicate;
        return re;
    }

    public Predicate ls(Directive predicate){
        Less re = new Less();
        re._left = this;
        re._right = predicate;
        return re;
    }

    public Predicate gte(Directive predicate){
        GreateOrEqual re = new GreateOrEqual();
        re._left = this;
        re._right = predicate;
        return re;
    }

    public Predicate lte(Directive predicate){
        LessOrEqual re = new LessOrEqual();
        re._left = this;
        re._right = predicate;
        return re;
    }

    public Predicate in(List<Directive> data) {
        Predicate _prefix = this;
        return new Predicate() {
            @Override
            public String script() {
                return String.format("%s in (%s)",
                    _prefix.script(), data.stream().map(Directive::script).collect(Collectors.joining(", ")));
            }

            @Override
            public List<Parameter<?>> parameters() {
                return data.stream().flatMap(d -> d.parameters().stream()).collect(Collectors.toList());
            }
        };
    }

    public Predicate like(Directive predicate){
        Like re = new Like();
        re._left = this;
        re._right = predicate;
        return re;
    }

    public <T> Predicate or(T value){
        Or re = new Or();
        re._left = this;
        re._right = pure(value);
        return re;
    }

    public <T> Predicate eq(T value){
        Equal re = new Equal();
        re._left = this;
        re._right = pure(value);
        return re;
    }

    public <T> Predicate ne(T value){
        NotEqual re = new NotEqual();
        re._left = this;
        re._right = pure(value);
        return re;
    }

    public <T> Predicate gt(T value){
        Great re = new Great();
        re._left = this;
        re._right = pure(value);
        return re;
    }

    public <T> Predicate lt(T value){
        Less re = new Less();
        re._left = this;
        re._right = pure(value);
        return re;
    }

    public <T> Predicate gte(T value){
        GreateOrEqual re = new GreateOrEqual();
        re._left = this;
        re._right = pure(value);
        return re;
    }

    public <T> Predicate lte(T value){
        LessOrEqual re = new LessOrEqual();
        re._left = this;
        re._right = pure(value);
        return re;
    }

    public <T> Predicate like(T value){
        Like re = new Like();
        re._left = this;
        re._right = pure(value);
        return re;
    }

    public <T> Predicate op(String operator, T value){
        Binary op = new Binary() {
            @Override
            protected String operator() {
                return operator;
            }
        };
        op._left = this;
        op._right = pure(value);
        return op;
    }

    public Predicate isNull(){
        IsNull re = new IsNull();
        re._prefix = this;
        return re;
    }

    public Predicate isNotNull(){
        IsNotNull re = new IsNotNull();
        re._prefix = this;
        return re;
    }

    private <T> Directive pure(T value){
        if(value instanceof Directive){
            return (Directive) value;
        }else {
            return new Literal(value);
        }
    }
}
