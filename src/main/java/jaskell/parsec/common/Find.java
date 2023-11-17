package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;

/**
 * Created by march on 16/9/9.
 * Find 算子跳过不匹配的内容，直到匹配成功或者 eof 。
 * 如果失败，Find 返回第一次开始尝试的位置和相关的 ParsecException。而非 EOFException
 */
public class Find<E, T>
    implements Parsec<E, T> {
    private final One<E> one;
    private final Parsec<E, T> parser;
    @Override
    public T parse(State<E> s) throws Exception {
        Integer start = s.status();
        try {
            while (true) {
                Integer tran = s.begin();
                try {
                    T re = parser.parse(s);
                    s.commit(tran);
                    return re;
                } catch (ParsecException e) {
                    s.rollback(tran);
                    one.parse(s);
                }
            }
        } catch (EOFException e) {
            String message = String.format("Parsec try from %s to end but failed", start);
            throw s.trap(message);
        }
    }

    public Find(Parsec<E, T> parser) {
        this.parser = parser;
        this.one = new One<>();
    }
}
