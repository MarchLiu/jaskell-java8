package jaskell.parsec.common;

import jaskell.parsec.ParsecException;
import jaskell.util.Try;

import java.io.EOFException;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mars Liu on 2016-01-03.
 * Enumerate 类型匹配形如 python 字符串定义 rf'abc' 前面的 rf 标记这样的枚举型片段，也可以制定分隔符来匹配 a|b|c 这样的形式
 */
public class Enumerate<E, T> implements Parsec<E, Set<T>> {

    List<Parsec<E, T>> parsecs;
    Parsec<E, ?> sep;

    public Enumerate(List<Parsec<E, T>> parsers) {
        this.parsecs = parsers;
        this.sep = new Return<>(null);
    }

    public Enumerate(List<Parsec<E, T>> parsers, Parsec<E, ?> sep) {
        this.parsecs = parsers;
        this.sep = sep;
    }

    @Override
    public Set<T> parse(State<E> s) throws EOFException, ParsecException {
        Set<T> result = new HashSet<>();
        try {
            List<Parsec<E, T>> parsers = parsecs.stream().map(Attempt::new).collect(Collectors.toList());
            Try<T> first = find1(parsers, s);
            if (first.isOk()) {
                result.add(first.get());
                parsers = parsecs.stream()
                        .map(p -> new Attempt<>(sep.then(p)))
                        .collect(Collectors.toList());
                Try<T> rs;
                do {
                    rs = find1(parsers, s);
                    if (rs.isOk()) {
                        result.add(rs.get());
                    }
                } while (rs.isOk());
                return result;
            } else {
                return new HashSet<>();
            }
        } catch (Throwable e) {
            throw s.trap(e.getMessage());
        }
    }

    private Try<T> find1(List<Parsec<E, T>> parsers, State<E> s) {
        for (Parsec<E, T> parser : parsers) {
            Try<T> re = parser.exec(s);
            if (re.isOk()) {
                return re;
            }
        }
        return Try.failure(s.trap("expect any enumerate item match at least"));
    }

    public Enumerate<E, T> by(Parsec<E, ?> sep) {
        return new Enumerate<>(parsecs, sep);
    }
}
