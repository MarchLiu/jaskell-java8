package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-03.
 * Between 算子等效于 open.then(p).over(close); 若 (open, parser, close) 组合子顺序解析成功,返回 parser 的解析结果.
 * 遵循 Haskell Parsec 的定义,我们将参数顺序设定为 between(open, close, parser),并提供了 curry 化的 In 子类型.
 */
public class Between<E, T, O, C> implements
    Parsec<E, T> {
    private final Parsec<E, O> open;
    private final Parsec<E, C> close;
    private final Parsec<E, T> parsec;

    @Override
    public T parse(State<E> s)
            throws Throwable {
        open.parse(s);
        T re = parsec.parse(s);
        close.parse(s);
        return re;
    }

    public Between(Parsec<E, O> open,
                   Parsec<E, C> close,
                   Parsec<E, T> parsec) {
        this.open = open;
        this.close = close;
        this.parsec = parsec;
    }

    static public class In<E, T, O, C, S extends State<E>> {
        private final Parsec<E, O> open;
        private final Parsec<E, C> close;

        public In(Parsec<E, O> open, Parsec<E, C> close) {
            this.open = open;
            this.close = close;
        }

        public Parsec<E, T> pack(Parsec<E, T> parser) {
            return new Between<>(this.open, this.close, parser);
        }
    }
}
