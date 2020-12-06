package jaskell.parsec.common;

import java.util.Optional;

/**
 * Created by march on 16/9/12.
 * helper toolbox for combinator.
 */
public class Combinator {
    public static <E, T> Attempt<E, T> attempt(Parsec<E, T> parser) {
        return new Attempt<>(parser);
    }

    public static <E, T> Ahead<E, T> ahead(Parsec<E, T> parser) {
        return new Ahead<>(parser);
    }

    @SafeVarargs
    public static <E, T> Choice<E, T> choice(Parsec<E, T>... parsers) {
        return new Choice<>(parsers);
    }

    public static <E, T> Many<E, T> many(Parsec<E, T> parser) {
        return new Many<>(parser);
    }

    public static <E, T> Many1<E, T> many1(Parsec<E, T> parser) {
        return new Many1<>(parser);
    }

    public static <E, T, L> ManyTill<E, T, L> manyTill(Parsec<E, T> parser, Parsec<E, L> end) {
        return new ManyTill<>(parser, end);
    }

    public static <E, T> Skip<E, T> skip(Parsec<E, T> parser) {
        return new Skip<>(parser);
    }

    public static <E, T> Skip1<E, T> skip1(Parsec<E, T> parser) {
        return new Skip1<>(parser);
    }

    public static <E, T, Sep> SepBy<E, T, Sep> sepBy(Parsec<E, T> parser, Parsec<E, Sep> by) {
        return new SepBy<>(parser, by);
    }

    public static <E, T, Sep> SepBy1<E, T, Sep> sepBy1(Parsec<E, T> parser, Parsec<E, Sep> by) {
        return new SepBy1<>(parser, by);
    }

    public static <E, T> Find<E, T> find(Parsec<E, T> parser) {
        return new Find<>(parser);
    }

    public static <E, T, O, C> Parsec<E, T> between(Parsec<E, O> open, Parsec<E, C> close,
        Parsec<E, T> parser){
        return new Between<>(open, close, parser);
    }

    public static <E, T> Parsec<E, Optional<T>> option(Parsec<E, T> parser){
        return new Option<>(parser);
    }
}
