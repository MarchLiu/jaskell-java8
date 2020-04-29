package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by march on 16/9/9.
 * Find 算子跳过不匹配的内容，直到匹配成功或者 eof 。
 * 如果失败，Find 返回第一次开始尝试的位置和相关的 ParsecException。而非 EOFException
 */
public class Find<T, E> implements Parsec<T, E> {
    private  One<E> one;
    private  Parsec<T, E> parser;
    @Override
    public <Status, Tran, S extends State<E, Status, Tran>> T parse(S s) throws ParsecException {
        Status start = s.status();
        try {
            while (true) {
                Tran tran = s.begin();
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

    public Find(Parsec<T, E> parser) {
        this.parser = parser;
        this.one = new One<>();
    }
}
