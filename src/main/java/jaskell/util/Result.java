package jaskell.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

// TODO: type safe
@SuppressWarnings("unchecked")
public class Result<E extends Throwable, T> implements Iterable<T>, Iterator<T> {
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
        Result<E, T> other = (Result<E, T>) (obj);
        return this.isOk() == other.isOk() && this.slot.equals(other.slot);
    }

    public <U> Result<E, U> map(Function<? super T, ? extends U> mapper) {
        if (_ok) {
            return new Result<>(mapper.apply((T)this.slot));
        } else {
            return new Result<>((E)this.slot);
        }
    }

    public <U> Result<E, U> flatMap(Function<? super T, Result<E, U>> mapper) {
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

    public T orElseGet(Result<? extends E, ?extends T> other) throws E {
        if(this._ok){
            return (T)this.slot;
        } else {
            return other.get();
        }
    }

    @Override
    public Iterator<T> iterator() {
        if(isOk()){
            return new Result<E, T>((T)slot);
        } else {
            return new Result<E, T>((E)slot);
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
