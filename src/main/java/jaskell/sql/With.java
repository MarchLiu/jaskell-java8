package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class With extends Query implements ThenSelect {
    Name _name;
    List<Name> _fields = new ArrayList<>();

    With(){

    }

    With(String name){
        _name = new Name(name);
    }

    With(Name name){
        _name = name;
    }

    With(String name, String fs){
        _name = new Name(name);
        _fields.addAll(Arrays.stream(fs.split(",")).map(Name::new).collect(Collectors.toList()));
    }

    With(Name name, String... fs){
        _name = name;
        _fields.addAll(Arrays.stream(fs).map(Name::new).collect(Collectors.toList()));
    }

    With(Name name, Name... fs){
        _name = name;
        _fields.addAll(Arrays.asList(fs));
    }

    public Name name(){
        return _name;
    }

    public With name(String name){
        _name = new Name(name);
        return this;
    }

    public With name(Name name){
        _name = name;
        return this;
    }

    public As as(Query query){
        As re =  new As(query);
        re._prefix = this;
        return re;
    }

    Recursive recursive(){
        Recursive re = new Recursive();
        re._prefix = this;
        return re;
    }

    @Override
    public String script() {
        if(_fields.isEmpty()) {
            return String.format("with %s", _name.script());
        }else{
            return String.format("with %s(%s)", _name.script(),
                    _fields.stream().map(Directive::script).collect(Collectors.joining(", ")));
        }
    }

    @Override
    public List<Parameter> parameters() {
        return new ArrayList<>();
    }

    public static class Recursive extends With {
        With _prefix;

        Recursive(){

        }

        Recursive(String name, String fs){
            super(name, fs);
        }

        Recursive(Name name, String... fs){
            super(name, fs);
        }

        Recursive(Name name, Name... fs){
            super(name, fs);
        }

        @Override
        public String script() {
            if(_fields.isEmpty()) {
                return String.format("with recursive %s", _name.script());
            }else{
                return String.format("with recursive %s(%s)", _name.script(),
                        _fields.stream().map(Directive::script).collect(Collectors.joining(", ")));
            }
        }
    }

    public static class As implements Directive {
        Directive _prefix;
        Query _query;
        As(Query query){
            _query = query;
        }

        @Override
        public String script() {
            return String.format("%s as (%s)", _prefix.script(), _query.script());
        }

        @Override
        public List<Parameter> parameters() {
            List<Parameter> re = _prefix.parameters();
            re.addAll(_query.parameters());
            return re;
        }

        public CommonTableExpression cte(String name){
            CommonTableExpression re = new CommonTableExpression(name);
            re._prefix = this;
            return re;
        }

        public CommonTableExpression cte(Name name){
            CommonTableExpression re = new CommonTableExpression(name);
            re._prefix = this;
            return re;
        }


        public CommonTableExpression cte(String name, String fs){
            CommonTableExpression re = new CommonTableExpression(name, fs);
            re._prefix = this;
            return re;
        }

        public CommonTableExpression cte(Name name, String fs){
            CommonTableExpression re = new CommonTableExpression(name, fs);
            re._prefix = this;
            return re;
        }

        public CommonTableExpression cte(String name, String ... fs){
            CommonTableExpression re = new CommonTableExpression(name, fs);
            re._prefix = this;
            return re;
        }

        public CommonTableExpression cte(Name name, String ... fs){
            CommonTableExpression re = new CommonTableExpression(name, fs);
            re._prefix = this;
            return re;
        }

        public CommonTableExpression cte(String name, Name ... fs){
            CommonTableExpression re = new CommonTableExpression(name, fs);
            re._prefix = this;
            return re;
        }

        public CommonTableExpression cte(Name name, Name ... fs){
            CommonTableExpression re = new CommonTableExpression(name, fs);
            re._prefix = this;
            return re;
        }

        public CommonTableQuery query(Query query){
            CommonTableQuery re = new CommonTableQuery();
            re._prefix = this;
            re._query = query;
            return re;
        }

        public Select select(String names){
            Select re = new Select(names);
            re._prefix = this;
            return re;
        }

        public Select select(String... names){
            Select re = new Select(names);
            re._prefix = this;
            return re;
        }

        public Select select(Directive... names){
            Select re = new Select(names);
            re._prefix = this;
            return re;
        }

        public Insert insert() {
            Insert re = new Insert();
            re._prefix = this;
            return re;
        }

        public Update update(String name) {
            Update re = new Update(name);
            re._prefix = this;
            return re;
        }

        public Update update(Name name) {
            Update re = new Update(name);
            re._prefix = this;
            return re;
        }

        public Delete delete() {
            Delete re = new Delete();
            re._prefix = this;
            return re;
        }

    }

    public static class Insert extends jaskell.sql.Insert {
        Directive _prefix;

        @Override
        public String script() {
            return String.format("%s %s", _prefix.script(), super.script());
        }

        @Override
        public List<Parameter> parameters() {
            List<Parameter> re = _prefix.parameters();
            re.addAll(super.parameters());
            return re;
        }
    }

    public static class Delete extends jaskell.sql.Delete {
        Directive _prefix;

        @Override
        public String script() {
            return String.format("%s %s", _prefix.script(), super.script());
        }

        @Override
        public List<Parameter> parameters() {
            List<Parameter> re = _prefix.parameters();
            re.addAll(super.parameters());
            return re;
        }

    }

    public static class Update extends jaskell.sql.Update {
        Directive _prefix;

        Update(String name) {
            super(name);
        }

        Update(Name name) {
            super(name);
        }

        @Override
        public String script() {
            return String.format("%s %s", _prefix.script(), super.script());
        }

        @Override
        public List<Parameter> parameters() {
            List<Parameter> re = _prefix.parameters();
            re.addAll(super.parameters());
            return re;
        }
    }

    public static class CommonTableExpression implements Directive {
        Directive _prefix;
        Name _name;
        List<Name> _fields = new ArrayList<>();

        public As as(Query query){
            As re =  new As(query);
            re._prefix = this;
            return re;
        }

        CommonTableExpression(String name){
            this._name = new Name(name);
        }

        CommonTableExpression(Name name){
            this._name = name;
        }

        CommonTableExpression(String name, String fs){
            this._name = new Name(name);
            this._fields.addAll(Arrays.stream(fs.split(",")).map(Name::new).collect(Collectors.toList()));
        }

        CommonTableExpression(String name, String... fs){
            this._name = new Name(name);
            this._fields.addAll(Arrays.stream(fs).map(Name::new).collect(Collectors.toList()));
        }

        CommonTableExpression(String name, Name... fs){
            this._name = new Name(name);
            this._fields.addAll(Arrays.asList(fs));
        }

        CommonTableExpression(Name name, String fs){
            this._name = name;
            this._fields.addAll(Arrays.stream(fs.split(",")).map(Name::new).collect(Collectors.toList()));
        }

        CommonTableExpression(Name name, String... fs){
            this._name = name;
            this._fields.addAll(Arrays.stream(fs).map(Name::new).collect(Collectors.toList()));
        }

        CommonTableExpression(Name name, Name... fs){
            this._name = name;
            this._fields.addAll(Arrays.asList(fs));
        }

        @Override
        public String script() {
            return String.format("%s, %s", _prefix.script(), _name.script());
        }

        @Override
        public List<Parameter> parameters() {
            return _prefix.parameters();
        }
    }

    public static class CommonTableQuery extends Query {
        Directive _prefix;
        Query _query;

        @Override
        public String script() {
            return String.format("%s %s", _prefix.script(), _query.script());
        }

        @Override
        public List<Parameter> parameters() {
            List<Parameter> re = _prefix.parameters();
            re.addAll(_query.parameters());
            return re;
        }
    }

}
