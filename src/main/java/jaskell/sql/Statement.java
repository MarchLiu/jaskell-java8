package jaskell.sql;

import jaskell.script.Directive;
import jaskell.script.Parameter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public abstract class Statement implements Directive {

    public PreparedStatement prepare(Connection connection) throws SQLException {
        return connection.prepareStatement(this.script());
    }

    public boolean execute(Connection connection) throws SQLException, IllegalStateException {
        try(PreparedStatement statement = connection.prepareStatement(this.script())) {
            syncParameters(statement);
            return statement.execute();
        }
    }

    public boolean execute(PreparedStatement statement) throws SQLException, IllegalStateException {
        syncParameters(statement);
        return statement.execute();
    }

    public <T> Statement setParameter(Object key, T value) throws IllegalArgumentException{
        boolean flag = false;
        for (Parameter<?> parameter:parameters()) {
            if (Objects.equals(parameter.key(), key)) {
                if(parameter.valueClass().isInstance(value)) {
                    @SuppressWarnings("unchecked")
                    Parameter<T> p = (Parameter<T>) parameter;
                    p.value(value);
                } else {
                    throw new IllegalArgumentException(String.format("parameter %s typed %s but received %s",
                            parameter.key(), parameter.valueClass().getName(), value.getClass().getName()));
                }
                flag = true;
            }
        }
        if(!flag){
            throw new IllegalArgumentException(String.format("parameter named %s not found", key));
        }
        return this;
    }

    public void clear(PreparedStatement statement) throws SQLException {
        statement.clearParameters();
    }

    public void syncParameters(PreparedStatement statement) throws SQLException {
        List<Parameter<?>> parameters = parameters();
        for (Parameter<?> parameter: parameters){
            if(!parameter.confirmed()){
                throw new IllegalStateException(String.format("parameter %s has not value", parameter.key()));
            }
        }
        clear(statement);
        List<Parameter<?>> params = parameters();
        setOrder(params);
        for (Parameter<?> parameter: params) {
            //TODO: overload by parameter.valueClass
            statement.setObject(parameter.order(), parameter.value());
        }

    }

    void setOrder(List<Parameter<?>> parameters){
        for (int i = 0; i < parameters.size(); i++) {
            parameters.get(i).order(i+1);
        }
    }

    public Returning returning(String names){
        Returning re =  new Returning(names);
        re._prefix = this;
        return re;
    }

    public Returning returning(String ... names){
        Returning re =  new Returning(names);
        re._prefix = this;
        return re;
    }

    public Returning returning(Directive names){
        Returning re =  new Returning(names);
        re._prefix = this;
        return re;
    }

    public Statement cache(){
        Statement self = this;
        return new Statement() {
            private final String _script = self.script();
            private final List<Parameter<?>> _parameters = self.parameters();
            @Override
            public String script() {
                return _script;
            }

            @Override
            public List<Parameter<?>> parameters() {
                return _parameters;
            }
        };
    }
}
