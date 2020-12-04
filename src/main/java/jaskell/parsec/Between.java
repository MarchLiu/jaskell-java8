package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-03.
 * Between 算子等效于 open.then(p).over(close); 若 (open, parser, close) 组合子顺序解析成功,返回 parser 的解析结果.
 * 遵循 Haskell Parsec 的定义,我们将参数顺序设定为 between(open, close, parser),并提供了 curry 化的 In 子类型.
 */
public class Between<E, T, O, C, Status, Tran> implements Parsec<E, T, Status, Tran> {
    private final Parsec<E, O, Status, Tran> open;
    private final Parsec<E, C, Status, Tran> close;
    private final Parsec<E, T, Status, Tran> parsec;

    @Override
    public T parse(State<E, Status, Tran> s)
            throws EOFException, ParsecException {
        open.parse(s);
        T re = parsec.parse(s);
        close.parse(s);
        return re;
    }

    public Between(Parsec<E, O, Status, Tran> open,
                   Parsec<E, C, Status, Tran> close,
                   Parsec<E, T, Status, Tran> parsec) {
        this.open = open;
        this.close = close;
        this.parsec = parsec;
    }

    static public class In<E, T, O, C, Status, Tran> {
        private final Parsec<E, O, Status, Tran> open;
        private final Parsec<E, C, Status, Tran> close;

        public In(Parsec<E, O, Status, Tran> open, Parsec<E, C, Status, Tran> close) {
            this.open = open;
            this.close = close;
        }

        public Parsec<E, T, Status, Tran> pack(Parsec<E, T, Status, Tran> parser) {
            return new Between<>(this.open, this.close, parser);
        }
    }
}
