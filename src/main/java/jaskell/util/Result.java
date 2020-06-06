package jaskell.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

// TODO: type safe
@SuppressWarnings("unchecked")
public class Result<T, E extends Throwable> implements Iterable<T>, Iterator<T> {
    private final Object slot;
    private final boolean _ok;
    private int idx = 0;

    public Result(T value) {
        this.slot = value;
        this._ok = true;
    }

    public Result(E error) {
        this.slot = error;
        this._ok = false;
    }
    
    public Optional<T> ok(){
        if(_ok){
            return Optional.of((T)slot);
        } else {
            return Optional.empty();
        }
    }

    public Optional<E> err(){
        if(_ok) {
            return Optional.empty();
        } else {
            return Optional.of((E)slot);
        }
    }

    public boolean isOk(){
        return this._ok;
    }

    public boolean isErr(){
        return !_ok;
    }

    public boolean equals(Object obj) {
        if (!this.slot.getClass().isInstance(obj)) {
            return false;
        }
        Result<T, E> other = (Result<T, E>) (obj);
        return this.isOk() == other.isOk() && this.slot.equals(other.slot);
    }

    public <U> Result<U, E> map(Function<? super T, ? extends U> mapper) {
        if (_ok) {
            return new Result<>(mapper.apply((T)this.slot));
        } else {
            return new Result<>((E)this.slot);
        }
    }

    public <U> Result<U, E> flatMap(Function<? super T, Result<U, E>> mapper) {
        if (_ok) {
            return mapper.apply((T)this.slot);
        } else {
            return new Result<>((E) this.slot);
        }
    }

    public T get() throws E{
        if(this._ok) {
            return (T)this.slot;
        } else {
            throw (E)this.slot;
        }
    }

    public T orElse(T value) {
        if(this._ok){
            return (T)this.slot;
        } else {
            return value;
        }
    }

    public T orElseGet(Result<?extends T, ? extends E> other) throws E {
        if(this._ok){
            return (T)this.slot;
        } else {
            return other.get();
        }
    }

    @Override
    public Iterator<T> iterator() {
        if(isOk()){
            return new Result<T, E>((T)slot);
        } else {
            return new Result<T, E>((E)slot);
        }
    }

    @Override
    public boolean hasNext() {
        return isOk() && idx == 0;
    }

    @Override
    public T next() {
        if (isOk() && idx == 0) {
            idx ++;
            return (T)slot;
        } else {
            throw new NoSuchElementException();
        }
    }
}
