package jaskell.sql;

import jaskell.script.Directive;

import javax.naming.SizeLimitExceededException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Select extends Query {
    private final List<Directive> _fields = new ArrayList<>();

    Select(){
    }

    Select(String names){
        _fields.addAll(Arrays.stream(names.split(","))
                .map(String::trim)
                .map(Name::new)
                .collect(Collectors.toList()));
    }

    Select(String... names){
        _fields.addAll(Arrays.stream(names).map(Name::new).collect(Collectors.toList()));
    }

    Select(Directive... directives){
        _fields.addAll(Arrays.asList(directives));
    }

    public Select select(String names){
        _fields.addAll(Arrays.stream(names.split(","))
                .map(String::trim)
                .map(Name::new)
                .collect(Collectors.toList()));
        return this;
    }

    public Select select(String ... names){
        _fields.addAll(Arrays.stream(names).map(Name::new).collect(Collectors.toList()));
        return this;
    }

    public Select select(Directive ... directives){
        _fields.addAll(Arrays.asList(directives));
        return this;
    }

    public Select all(){
        _fields.add(new Literal("*"));
        return this;
    }

    public From from(String name){
        From re = new From();
        re._from = new Name(name);
        re._select = this;
        return re;
    }

    public From from(Directive f) {
        From re = new From();
        re._select = this;
        re._from = f;
        return re;
    }

    public Where where(Predicate predicate){
        Where re = new Where(predicate);
        re._prefix = this;
        re._predicate = predicate;
        return re;
    }

    @Override
    public String script() {
        return String.format("select %s",
                _fields.stream().map(Directive::script).collect(Collectors.joining(", ")));
    }

    @Override
    public List<jaskell.script.Parameter<?>> parameters() {
        List<jaskell.script.Parameter<?>> re = new ArrayList<>();
        for (Directive field : _fields) {
            re.addAll(field.parameters());
        }
        setOrder(re);
        return re;
    }

    public static class From extends Query implements CouldGroup, CouldOrder {
        Select _select;
        Directive _from;

        @Override
        public String script() {
            return String.format("%s from %s", _select.script(), _from.script());
        }

        @Override
        public List<jaskell.script.Parameter<?>> parameters() {
            ArrayList<jaskell.script.Parameter<?>> re = new ArrayList<>();
            re.addAll(_select.parameters());
            re.addAll(_from.parameters());
            for (int i = 0; i < re.size(); i++) {
                re.get(i).order(i);
            }
            return re;
        }

        public Join join(Directive other){
            Join re = new Join();
            re._prefix = this;
            re._join = other;
            return re;
        }

        public Left left(){
            Left re = new Left();
            re._prefix = this;
            return re;
        }

        public Right right(){
            Right re = new Right();
            re._prefix = this;
            return re;
        }

        public Full full(){
            Full re = new Full();
            re._prefix = this;
            return re;
        }

        public Inner inner(){
            Inner re = new Inner();
            re._prefix = this;
            return re;
        }

        public Cross cross(){
            Cross re = new Cross();
            re._prefix = this;
            return re;
        }

        public Where where(Predicate predicate){
            Where re = new Where(predicate);
            re._prefix = this;
            re._predicate = predicate;
            return re;
        }

        public Group group() {
            Group re = new Group();
            re._prefix = this;
            return re;
        }

        public Order order() {
            Order re = new Order();
            re._prefix = this;
            return re;
        }
    }


}
