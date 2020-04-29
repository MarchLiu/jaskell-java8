package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-03.
 * Between 算子等效于 open.then(p).over(close); 若 (open, parser, close) 组合子顺序解析成功,返回 parser 的解析结果.
 * 遵循 Haskell Parsec 的定义,我们将参数顺序设定为 between(open, close, parser),并提供了 curry 化的 In 子类型.
 */
public class Between<T, E, O, C> implements Parsec<T, E> {
    private Parsec<O, E> open;
    private Parsec<C, E> close;
    private Parsec<T, E> parsec;

    @Override
    public <Status, Tran, S extends State<E, Status, Tran>> T parse(S s)
            throws EOFException, ParsecException {
        open.parse(s);
        T re = parsec.parse(s);
        close.parse(s);
        return re;
    }

    public Between(Parsec<O, E> open,
                   Parsec<C, E> close,
                   Parsec<T, E> parsec) {
        this.open = open;
        this.close = close;
        this.parsec = parsec;
    }

    static public class In<T, E, O, C, Status, Tran> {
        private Parsec<O, E> open;
        private Parsec<C, E> close;
        public In(Parsec<O, E> open, Parsec<C, E> close) {
            this.open = open;
            this.close = close;
        }
        public Parsec<T, E> pack(Parsec<T, E> parser) {
            return new Between<>(this.open, this.close, parser);
        }
    }
}
