package jaskell.parsec;

import java.io.EOFException;
import java.util.List;

/**
 * Created by Mars Liu on 2016-01-02.
 * BasicState 类型提供一个基本的 state 实现,它将线性信息序列放到一个List&lt;E%gt;中作为缓存,不保证并发安全.
 * 如果我们要处理的数据量不至于对内存使用造成负担,也没有并发安全的需要,可以使用它.
 */
public class SimpleState<E> implements State<E, Integer, Integer> {
    private final List<E> buffer;
    private int current=0;
    private int tran = -1;

    @Override
    public E next() throws EOFException {
        if (this.current >= this.buffer.size()) {
            throw new EOFException();
        }
        E re = this.buffer.get(this.current);
        this.current++;
        return re;
    }

    @Override
    public Integer status() {
        return this.current;
    }

    @Override
    public Integer begin() {
        if(this.tran == -1){
            this.tran = this.current;
        }
        return this.current;
    }

    @Override
    public Integer begin(Integer tran) {
        if(this.tran > tran){
            this.tran = tran;
        }
        return this.tran;
    }

    @Override
    public void rollback(Integer tran) {
        if(this.tran == tran) {
            this.tran = -1;
        }
        this.current = tran;
    }

    @Override
    public void commit(Integer tran) {
        if(this.tran == tran) {
            this.tran = -1;
        }
    }

    @Override
    public ParsecException trap(String message) {
        return new ParsecException(this.current, message);
    }

    public SimpleState(List<E> items){
        this.buffer = items;
    }


}
