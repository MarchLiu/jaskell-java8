package jaskell.parsec;

import java.io.EOFException;

/**
 * Created by Mars Liu on 2016-01-02.
 * State 是 state 接口规范, parsec 用户可以定义自己的 state 实现.
 */
public interface State<E, Status, Tran> {
    /**
     * @return 总是返回 state 的当前元素，并迭代到下一个位置。如果到达结尾，会抛出 eof。
     * @throws java.io.EOFException 到达终结状态时，再调用 next 会抛出 EOF 异常。
     */
    E next() throws EOFException;

    /**
     * @return 返回当前 state 的状态，对于线性序列，通常即是索引。调用 next 时，会返回这个状态对应的元素。
     */
    Status status();

    /**
     * @return 返回一个事务标示，state 应记录和管理这个事务。
     */
    Tran begin();

    /**
     * 在很多场合，调用者使用自动生成的事务标示已经足够，但是 jparsec 也提供了显式指定事务标示的接口。
     * 在通常的线性序列状态中，通常不需要这样的模式，但是对于具名事务环境，例如一些数据库，可能这种方式
     * 是最佳的实践。State 应尽可能的使用给定的标示构造事务，并返回最终得到的事务标示，这表示我们允许
     * State 给出与期待不同的事务标示。
     * @param tran 接受一个事务标示参数，state 应尽可能使用这个标示生成事务。
     * @return 返回一个事务标示，state 应记录和管理这个事务。
     */
    Tran begin(Tran tran);

    /**
     * @param tran 提交指定的事务号。
     */
    void commit(Tran tran);

    /**
     * @param tran 回滚指定事务号对应的事务。
     */
    void rollback(Tran tran);

    /**
     * @param message 接受指定的消息文本，用于构造异常
     * @return 总是返回一个 ParsecException 或它的子类。具体的 State 实现者可以提供继承自 ParsecException 的异常。
     */
    ParsecException trap(String message);
}
