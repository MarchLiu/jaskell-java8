package jaskell.parsec;

import java.util.List;
import java.util.Optional;

/**
 * Created by march on 16/9/12.
 * helper toolbox for combinator.
 */
public class Combinator {
    public static <T, E, Status, Tran> Try<T, E, Status, Tran> attempt(Parsec<T, E, Status, Tran> parser) {
        return new Try<>(parser);
    }

    public static <T, E, Status, Tran> Ahead<T, E, Status, Tran> ahead(Parsec<T, E, Status, Tran> parser) {
        return new Ahead<>(parser);
    }

    @SafeVarargs
    public static <T, E, Status, Tran> Choice<T, E, Status, Tran> choice(Parsec<T, E, Status, Tran> ... parsers) {
        return new Choice<>(parsers);
    }

    public static <T, E, Status, Tran> Many<T, E, Status, Tran> many(Parsec<T, E, Status, Tran> parser) {
        return new Many<>(parser);
    }

    public static <T, E, Status, Tran> Many1<T, E, Status, Tran> many1(Parsec<T, E, Status, Tran> parser) {
        return new Many1<>(parser);
    }

    public static <T, L, E, Status, Tran> ManyTill<T, L, E, Status, Tran> manyTill(
            Parsec<T, E, Status, Tran> parser, Parsec<L, E, Status, Tran> end) {
        return new ManyTill<>(parser, end);
    }

    public static <T, E, Status, Tran> Skip<T, E, Status, Tran> skip(Parsec<T, E, Status, Tran> parser) {
        return new Skip<>(parser);
    }

    public static <T, E, Status, Tran> Skip1<T, E, Status, Tran>
    skip1(Parsec<T, E, Status, Tran> parser) {
        return new Skip1<>(parser);
    }

    public static <T, Sep, E, Status, Tran> SepBy<T, Sep, E, Status, Tran> sepBy(
            Parsec<T, E, Status, Tran> parser, Parsec<Sep, E, Status, Tran> by) {
        return new SepBy<>(parser, by);
    }

    public static <T, Sep, E, Status, Tran> SepBy1<T, Sep, E, Status, Tran> sepBy1(
        Parsec<T, E, Status, Tran> parser, Parsec<Sep, E, Status, Tran> by) {
        return new SepBy1<>(parser, by);
    }

    public static <T, E, Status, Tran> Find<T, E, Status, Tran> find(Parsec<T, E, Status, Tran> parser) {
        return new Find<>(parser);
    }

    public static <T, E, O, C, Status, Tran> Between<T, O, C, E, Status, Tran> between(
        Parsec<O, E, Status, Tran> open, Parsec<C, E, Status, Tran> close,
        Parsec<T, E, Status, Tran> parser){
        return new Between<>(open, close, parser);
    }

    public static <T, E, Status, Tran> Option<T, E, Status, Tran> option(Parsec<T, E, Status, Tran> parser){
        return new Option<>(parser);
    }
}
