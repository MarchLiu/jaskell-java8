package jaskell.script;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Parameter<T> implements Directive {
    private Optional<T> _value = null;
    private Object _key;
    private int _order;
    private Class<T> _cls;
    private String _placeHolder;

    protected Parameter(){}

    public Parameter(String placeHolder, Object key, Class<T> cls){
        this._placeHolder = placeHolder;
        _key = key;
        _cls = cls;
    }

    public T value(){
        return _value.orElse(null);
    }

    public void value(T v){
        _value = Optional.ofNullable(v);
    }

    public boolean confirmed(){
        return _value != null;
    }

    public String placeHolder(){
        return _placeHolder;
    }

    public Class<T> valueClass(){
        return _cls;
    }

    public Object key(){
        return _key;
    }

    public int order(){
        return _order;
    }

    public void order(int o){
        this._order = o;
    }

    @Override
    public String script() {
        return _placeHolder;
    }

    @Override
    public List<Parameter<?>> parameters() {
        List<Parameter<?>> re =  new ArrayList<>();
        re.add(this);
        return re;
    }

}
